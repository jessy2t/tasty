package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.dataModels.AddressDataModel;
import com.acme.tasty.dataModels.CategoriesDataModel;
import com.acme.tasty.dataModels.RestaurantAttributesDataModel;
import com.acme.tasty.dataModels.RestaurantDataModel;

import java.util.List;

public class RestaurantAttributesDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="restaurant_attributes.db";
    public Context Context;
    public RestaurantAttributesDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        Context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table attributes(attributes_id INTEGER primary key, has_delivery_service BOOLEAN," +
                "supports_reservation BOOLEAN, reservation_necessary BOOLEAN, supports_in_app_payment BOOLEAN," +
                "vegetarian BOOLEAN, vegan BOOLEAN, categories_id INTEGER, foreign key(categories_id) references " +
                "categories(categories_id))");
        insertData(true, true, true, false,
                true, true, 1, db);
        insertData(true, true, true, false,
                true, true, 2, db);
        insertData(false, true, true, false,
                true, false, 6, db);
        insertData(true, false, true, false,
                true, true, 3, db);
        insertData(false, false, true, false,
                true, true, 7, db);
        insertData(true, true, true, false,
                true, true, 4, db);
        insertData(true, true, true, false,
                true, true, 5, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists restaurant_owner");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from attributes");
    }

    public Boolean insertData(Boolean has_delivery_services, Boolean supports_reservation, Boolean reservation_necessary,
                              Boolean supports_in_app_payment, Boolean vegetarian, Boolean vegan, Integer categories_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("has_delivery_service", has_delivery_services);
        values.put("supports_reservation", supports_reservation);
        values.put("reservation_necessary", reservation_necessary);
        values.put("supports_in_app_payment", supports_in_app_payment);
        values.put("vegetarian", vegetarian);
        values.put("vegan", vegan);
        values.put("categories_id", categories_id);

        long result = db.insert("attributes", null, values);
        if(result == 1) return false;
        else
            return true;
    }

    public Boolean insertData(Boolean has_delivery_services, Boolean supports_reservation, Boolean reservation_necessary,
                              Boolean supports_in_app_payment, Boolean vegetarian, Boolean vegan, Integer categories_id,
                              SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("has_delivery_service", has_delivery_services);
        values.put("supports_reservation", supports_reservation);
        values.put("reservation_necessary", reservation_necessary);
        values.put("supports_in_app_payment", supports_in_app_payment);
        values.put("vegetarian", vegetarian);
        values.put("vegan", vegan);
        values.put("categories_id", categories_id);

        long result = db.insert("attributes", null, values);
        if(result == -1) return false;
        else
            return true;
    }

    public RestaurantAttributesDataModel getRestaurantAttributes(Integer attributesId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from attributes where attributes_id=?",
                new String[] {String.valueOf(attributesId)});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        Boolean hasDeliveryService = cursor.getInt(1) > 0;
        Boolean supportsReservation = cursor.getInt(2) > 0;
        Boolean reservationNecessary = cursor.getInt(3) > 0;
        Boolean supportsInAppPayment = cursor.getInt(4) > 0;
        Boolean vegetarian = cursor.getInt(5) > 0;
        Boolean vegan = cursor.getInt(6) > 0;
        Integer categoriesId = cursor.getInt(7);

        CategoriesDataModel categories = new CategoriesDBHelper(Context).getCategories(categoriesId);

        return new RestaurantAttributesDataModel(hasDeliveryService, supportsReservation, reservationNecessary,
                supportsInAppPayment, vegetarian, vegan, categories);
    }

    public Integer getId(Boolean has_delivery_services, Boolean supports_reservation, Boolean reservation_necessary,
                                               Boolean supports_in_app_payment, Boolean vegetarian, Boolean vegan, Integer categories_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from attributes where has_delivery_service=? AND supports_reservation=? AND reservation_necessary=? AND supports_in_app_payment=? AND vegetarian=? AND vegan=? AND categories_id=?",
                new String[] {String.valueOf(has_delivery_services), String.valueOf(supports_reservation), String.valueOf(reservation_necessary), String.valueOf(supports_in_app_payment), String.valueOf(vegetarian), String.valueOf(vegan), String.valueOf(categories_id)});

        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        Integer id = cursor.getInt(0);

        return id;
    }
}
