package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.MainActivity;
import com.acme.tasty.SwipePreisrahmen;
import com.acme.tasty.dataModels.RestaurantDataModel;
import com.acme.tasty.dataModels.SuggestionBasisDataModel;
import com.acme.tasty.dataModels.SuggestionDataModel;

public class SuggestionDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="suggestion.db";

    public SuggestionDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table suggestion(suggestion_id INTEGER primary key, restaurant_name TEXT," +
                "suggestion_basis_id INTEGER, foreign key (restaurant_name) references restaurant(restaurant_name)," +
                "foreign key (suggestion_basis_id) references suggestion_basis(suggestion_basis_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists suggestion");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from suggestion");
    }

    public Boolean insertData(String restaurantName, Integer suggestionBasisId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("restaurant_name", restaurantName);
        values.put("suggestion_basis_id", suggestionBasisId);

        long result = db.insert("suggestion", null, values);
        return result != -1;
    }

    public SuggestionDataModel getSuggestion(Integer suggestionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from suggestion where suggestion_id=?",
                new String[] {String.valueOf(suggestionId)});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        String restaurantName = cursor.getString(1);
        Integer suggestionBasisId = cursor.getInt(2);
        cursor.close();
        RestaurantDataModel restaurant = MainActivity.RestaurantDB.getRestaurant(restaurantName);
        SuggestionBasisDataModel suggestionBasis = SwipePreisrahmen.SuggestionBasisDB.getSuggestionBasis(suggestionBasisId);

        return new SuggestionDataModel(restaurant, suggestionBasis);
    }
}
