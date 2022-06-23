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

    public RestaurantDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createRestaurantTable(db);
        createAttributesTable(db);
    }

    private void createRestaurantTable(SQLiteDatabase db) {
        db.execSQL("create table restaurant(restaurant_name TEXT primary key," +
                "image_name TEXT, attributes_id INTEGER, address_id INTEGER, foreign key(attributes_id) references " +
                "attributes(attributes_id), foreign key(address_id) references address(address_id))");
        insertData("Tony's Tacos", "taco", 1, 1, db);
        insertData("Billy's Burger", "burger", 2, 2, db);
        insertData("Hansi's Wurstbude", "sausage", 3, 3, db);
        insertData("Curry Murry", "curry", 3, 3, db);
        insertData("Chinese Rises", "rice", 4, 4, db);
        insertData("Indonesian Food", "indonesian", 5, 5, db);
        insertData("Pizza Bellissima", "pizza", 6, 6, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists restaurant_owner");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from restaurant");
    }

    private void createAttributesTable(SQLiteDatabase db) {
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
        return result != -1;
    }

    public long insertData(String restaurantName, String imageName, Integer attributesId, Integer addressId,
                           SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("restaurant_name", restaurantName);
        values.put("image_name", imageName);
        values.put("attributes_id", attributesId);
        values.put("address_id", addressId);

        long result = db.insert("restaurant", null, values);
        if(result == -1) return 0;
        else
            return result;
    }

    public Boolean insertData(String restaurantName, Integer attributesId, Integer addressId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("restaurant_name", restaurantName);
        values.put("image_name", "unknown_restaurant");
        values.put("attributes_id", attributesId);
        values.put("address_id", addressId);

        long result = db.insert("restaurant", null, values);
        return result != -1;
    }

    public RestaurantDataModel getRestaurant(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from restaurant where restaurant_name=?", new String[] {name});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        String restaurantImage = cursor.getString(1);
        Integer attributesId = cursor.getInt(2);
        Integer addressId = cursor.getInt(3);
        cursor.close();

        AddressDataModel address = MainActivity.AddressDB.getAddress(addressId);
        RestaurantAttributesDataModel attributes = MainActivity.AttributesDB.getRestaurantAttributes(attributesId);

        return new RestaurantDataModel(name, restaurantImage, attributes, address);
    }

    public RestaurantDataModel getRestaurantBySuggestionBasis(SuggestionBasisDataModel suggestionBasis) {
        String SQLQuery = configureSQLQuery(suggestionBasis.DeliveryOrReservation, suggestionBasis.DietPreference);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQLQuery, null);
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        String name = cursor.getString(0);
        String restaurantImage = cursor.getString(1);
        Integer attributesId = cursor.getInt(2);
        Integer addressId = cursor.getInt(3);
        cursor.close();

        AddressDataModel address = MainActivity.AddressDB.getAddress(addressId);
        RestaurantAttributesDataModel attributes = MainActivity.AttributesDB.getRestaurantAttributes(attributesId);

        return new RestaurantDataModel(name, restaurantImage, attributes, address);
    }

    private String configureSQLQuery(String deliveryOrReservation, String dietPreference) {
        String SQLQuery = "select restaurant.* from restaurant join attributes on restaurant.attributes_id=" +
                "attributes.attributes_id where ";

        if ("delivery".equals(deliveryOrReservation)) {
            SQLQuery += "attributes.has_delivery_service=1 ";
        } else {
            SQLQuery += "attributes.supports_reservation=1 ";
        }

        switch (dietPreference){
            case "vegan":
                SQLQuery += "and attributes.vegan=1 ";
                break;
            case "vegetarian":
                SQLQuery += "and attributes.vegetarian=1 ";
                break;
            default:
                break;
        }

        return SQLQuery + "order by random() limit 1";
    }
}
