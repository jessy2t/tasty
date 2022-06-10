package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.MainActivity;
import com.acme.tasty.dataModels.*;

public class RestaurantDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="restaurant.db";
    private Context Context;
    public RestaurantDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        Context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table restaurant(restaurant_name TEXT primary key," +
                "attributes_id INTEGER, address_id INTEGER, foreign key(attributes_id) references " +
                "attributes(attributes_id), foreign key(address_id) references address(address_id))");
        insertData("Tony's Tacos", 1, 1, db);
        insertData("Billy's Burger", 2, 2, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists restaurant_owner");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from restaurant");
    }

    public Boolean insertData(String restaurantName, Integer attributesId, Integer addressId, SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("restaurant_name", restaurantName);
        values.put("attributes_id", attributesId);
        values.put("address_id", addressId);

        long result = db.insert("restaurant", null, values);
        if(result == 1) return false;
        else
            return true;
    }

    public Boolean insertData(String restaurantName, Integer attributesId, Integer addressId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("restaurant_name", restaurantName);
        values.put("attributes_id", attributesId);
        values.put("address_id", addressId);

        long result = db.insert("restaurant", null, values);
        if(result == 1) return false;
        else
            return true;
    }

    public RestaurantDataModel getRestaurant(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from restaurant where restaurant_name=?", new String[] {name});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        Integer attributesId = cursor.getInt(1);
        Integer addressId = cursor.getInt(2);
        AddressDataModel address = MainActivity.AddressDB.getAddress(addressId);
        RestaurantAttributesDataModel attributes = MainActivity.AttributesDB.getRestaurantAttributes(attributesId);

        return new RestaurantDataModel(name, attributes, address);
    }
}
