package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.dataModels.AddressDataModel;
import com.acme.tasty.dataModels.CityDataModel;
import com.acme.tasty.dataModels.RatingDataModel;
import com.acme.tasty.dataModels.RestaurantDataModel;

import java.util.ArrayList;

public class AddressDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="address.db";
    private Context Context;
    public AddressDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        Context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table address(address_id INTEGER primary key, street TEXT," +
                "house_number INTEGER, zip_code TEXT, foreign key (zip_code) references city (zip_code))");
        insertData("Schlemmerstraße", 25, "12345", db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists address");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from address");
    }

    public Boolean insertData(String street, Integer houseNumber, String zipCode, SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("street", street);
        values.put("house_number", houseNumber);
        values.put("zip_code", zipCode);

        long result = db.insert("address", null, values);
        if(result == 1) return false;
        else
            return true;
    }

    public Boolean insertData(String street, Integer houseNumber, String zipCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("street", street);
        values.put("house_number", houseNumber);
        values.put("zip_code", zipCode);

        long result = db.insert("address", null, values);
        if(result == 1) return false;
        else
            return true;
    }

    public AddressDataModel getAddress(Integer addressId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from address where address_id=?",
                new String[] {String.valueOf(addressId)});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        String street = cursor.getString(1);
        Integer houseNumber = cursor.getInt(2);
        String zipCode = cursor.getString(3);
        CityDataModel city = new CityDBHelper(Context).getCity(zipCode);

        return new AddressDataModel(street, houseNumber, city);
    }
}
