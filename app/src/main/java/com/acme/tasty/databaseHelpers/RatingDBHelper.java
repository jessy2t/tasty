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
import java.util.Date;

public class RatingDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="rating.db";
    private Context Context;
    public RatingDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        Context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table rating(rating_id INTEGER primary key autoincrement, rating INTEGER," +
                "review_title TEXT, review_description TEXT, review_date DATE, restaurant TEXT," +
                "foreign key (restaurant) references restaurant (restaurant_name))");
        insertData(5.0, "Superlecker", "Lorem ipsum dolor sit amet, consectetur" +
                        "adipiscing elit, sed do eiusmod tempor\nincididunt ut labore et dolore magna aliqua.",
                "2022-05-05", "Tony's Tacos", db);
        insertData(2.0, "könnte schneller sein", "Lorem ipsum dolor sit amet, consectetur" +
                        "adipiscing elit, sed do eiusmod tempor\nincididunt ut labore et dolore magna aliqua.",
                "2022-06-06", "Tony's Tacos", db);
        insertData(3.0, "Bestes Restaurant in der Umgebung", "Lorem ipsum dolor sit amet, consectetur" +
                        "adipiscing elit, sed do eiusmod tempor\nincididunt ut labore et dolore magna aliqua.",
                "2022-04-04", "Tony's Tacos", db);
        insertData(2.5, "überteuert, sonst ok", "Lorem ipsum dolor sit amet, consectetur" +
                        "adipiscing elit, sed do eiusmod tempor\nincididunt ut labore et dolore magna aliqua.",
                "2022-05-28", "Tony's Tacos", db);
        insertData(4.0, "Authentische Küche!", "Lorem ipsum dolor sit amet, consectetur" +
                        "adipiscing elit, sed do eiusmod tempor\nincididunt ut labore et dolore magna aliqua.",
                "2022-06-05", "Tony's Tacos", db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists rating");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from rating");
    }

    public Boolean insertData(Double rating, String reviewTitle, String reviewDescription, String reviewDate,
                              String restaurant, SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("rating", rating);
        values.put("review_title", reviewTitle);
        values.put("review_description", reviewDescription);
        values.put("review_date", reviewDate);
        values.put("restaurant", restaurant);

        long result = db.insert("rating", null, values);
        if(result == 1) return false;
        else
            return true;
    }

    public ArrayList<RatingDataModel> getRatingOrderedByNewest(String restaurantName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from rating where restaurant=? order by review_date DESC",
                new String[] {restaurantName});
        Integer count = cursor.getCount();
        if(count <= 0)
            return null;

        cursor.moveToFirst();
        ArrayList result = new ArrayList();
        do {
            Float rating = cursor.getFloat(1);
            String reviewTitle = cursor.getString(2);
            String reviewDescription = cursor.getString(3);
            String reviewDate = cursor.getString(4);
            RestaurantDataModel restaurant = new RestaurantDBHelper(Context).getRestaurant(restaurantName);

            result.add(new RatingDataModel(rating, reviewTitle, reviewDescription, reviewDate, restaurant));

        } while (cursor.moveToNext());

        return result;
    }

    public ArrayList<RatingDataModel> getRatingOrderedByHighest(String restaurantName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from rating where restaurant=? order by rating DESC",
                new String[] {restaurantName});
        Integer count = cursor.getCount();
        if(count <= 0)
            return null;

        cursor.moveToFirst();
        ArrayList result = new ArrayList();
        do {
            Float rating = cursor.getFloat(1);
            String reviewTitle = cursor.getString(2);
            String reviewDescription = cursor.getString(3);
            String reviewDate = cursor.getString(4);
            RestaurantDataModel restaurant = new RestaurantDBHelper(Context).getRestaurant(restaurantName);

            result.add(new RatingDataModel(rating, reviewTitle, reviewDescription, reviewDate, restaurant));

        } while (cursor.moveToNext());

        return result;
    }

    public ArrayList<RatingDataModel> getRatingOrderedByLowest(String restaurantName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from rating where restaurant=? order by rating ASC",
                new String[] {restaurantName});
        Integer count = cursor.getCount();
        if(count <= 0)
            return null;

        cursor.moveToFirst();
        ArrayList result = new ArrayList();
        do {
            Float rating = cursor.getFloat(1);
            String reviewTitle = cursor.getString(2);
            String reviewDescription = cursor.getString(3);
            String reviewDate = cursor.getString(4);
            RestaurantDataModel restaurant = new RestaurantDBHelper(Context).getRestaurant(restaurantName);

            result.add(new RatingDataModel(rating, reviewTitle, reviewDescription, reviewDate, restaurant));

        } while (cursor.moveToNext());

        return result;
    }
}
