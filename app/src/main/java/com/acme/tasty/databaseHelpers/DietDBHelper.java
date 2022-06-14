package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.dataModels.AddressDataModel;
import com.acme.tasty.dataModels.CityDataModel;
import com.acme.tasty.dataModels.DietDataModel;

public class DietDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="diet.db";
    private Context Context;
    public DietDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        Context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table diet(diet_id INTEGER primary key, vegetarian BOOLEAN, vegan BOOLEAN, carnivore BOOLEAN," +
                "glutenfree BOOLEAN, lactosefree BOOLEAN, fruitarian BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists diet");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from diet");
    }

    public Boolean insertData(Boolean vegetarian, Boolean vegan, Boolean carnivore, Boolean glutenfree,
                              Boolean lactosefree, Boolean fruitarian) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("diet_id", 1);
        values.put("vegetarian", vegetarian);
        values.put("vegan", vegan);
        values.put("carnivore", carnivore);
        values.put("glutenfree", glutenfree);
        values.put("lactosefree", lactosefree);
        values.put("fruitarian", fruitarian);

        long result = db.insert("diet", null, values);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean updateData(Boolean vegetarian, Boolean vegan, Boolean carnivore, Boolean glutenfree,
                              Boolean lactosefree, Boolean fruitarian) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("vegetarian", vegetarian);
        values.put("vegan", vegan);
        values.put("carnivore", carnivore);
        values.put("glutenfree", glutenfree);
        values.put("lactosefree", lactosefree);
        values.put("fruitarian", fruitarian);

        Integer result = db.update("diet", values, "diet_id=1", null);
        if(result == 0) return false;
        else
            return true;
    }

    public DietDataModel getDiet() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from diet where diet_id=?",
                new String[] {String.valueOf(1)});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        Boolean vegetarian = cursor.getInt(1) > 0;
        Boolean vegan = cursor.getInt(2) > 0;
        Boolean carnivore = cursor.getInt(3) > 0;
        Boolean glutenfree = cursor.getInt(4) > 0;
        Boolean lactosefree = cursor.getInt(5) > 0;
        Boolean fruitarian = cursor.getInt(6) > 0;

        return new DietDataModel(vegetarian, vegan, carnivore, glutenfree, lactosefree, fruitarian);
    }
}
