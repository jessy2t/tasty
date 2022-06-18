package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.dataModels.CityDataModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="city.db";
    public CityDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table city(zip_code Text primary key, city_name TEXT unique)");
        insertData("12345", "Schlaraffenstadt", db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists city");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from city");
    }

    public Boolean insertData(String zip, String city, SQLiteDatabase db) {
        Pattern regexPattern = Pattern.compile("\\d{5}");
        Matcher patternMatcher = regexPattern.matcher(zip);
        if (!patternMatcher.find())
            return false;

        ContentValues values = new ContentValues();

        values.put("zip_code", zip);
        values.put("city_name", city);

        long result = db.insert("city", null, values);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean insertData(String zip, String city) {
        Pattern regexPattern = Pattern.compile("\\d{5}");
        Matcher patternMatcher = regexPattern.matcher(zip);
        if (!patternMatcher.find() || cityExists(zip, city))
            return false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("zip_code", zip);
        values.put("city_name", city);

        long result = db.insert("city", null, values);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean cityExists(String zip, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from city where zip_code=? or city_name=?", new String[] {zip, city});
        if(cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public CityDataModel getCity(String zip) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from city where zip_code=?", new String[] {zip});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        String zipCode = cursor.getString(0);
        String cityName = cursor.getString(1);

        return new CityDataModel(zipCode, cityName);
    }
}
