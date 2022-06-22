package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.dataModels.PriceRangeDataModel;

public class PriceRangeDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="price_range.db";

    public PriceRangeDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table price_range(price_range_id INTEGER primary key, min_price INTEGER, max_price INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists price_range");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from price_range");
    }

    public Boolean insertData(Integer minPrice, Integer maxPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("price_range_id", 1);
        values.put("min_price", minPrice);
        values.put("max_price", maxPrice);

        long result = db.insert("price_range", null, values);
        return result != -1;
    }

    public Boolean updateData(Integer minPrice, Integer maxPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("min_price", minPrice);
        values.put("max_price", maxPrice);

        int result = db.update("price_range", values, "price_range_id=1", null);
        return result != 0;
    }

    public PriceRangeDataModel getPriceRange() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from price_range where price_range_id=?",
                new String[] {String.valueOf(1)});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        Integer minPrice = cursor.getInt(1);
        Integer maxPrice = cursor.getInt(2);
        cursor.close();

        return new PriceRangeDataModel(minPrice, maxPrice);
    }
}
