package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.*;
import com.acme.tasty.dataModels.CategoriesDataModel;
import com.acme.tasty.dataModels.DietDataModel;
import com.acme.tasty.dataModels.PriceRangeDataModel;
import com.acme.tasty.dataModels.SuggestionBasisDataModel;

public class SuggestionBasisDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="suggestion_basis.db";
    private Context Context;
    public SuggestionBasisDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        Context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table suggestion_basis(suggestion_basis_id INTEGER primary key, delivery_or_reservation TEXT," +
                "food_or_restaurant_suggestion TEXT, diet_preference TEXT, min_price INTEGER, max_price INTEGER," +
                "categories_id INTEGER, fixed_diet_id INTEGER, fixed_price_range_id INTEGER, foreign key(categories_id) " +
                "references categories(categories_id), foreign key(fixed_diet_id) references diet(diet_id)," +
                "foreign key(fixed_price_range_id) references price_range(price_range_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists suggestion_basis");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from suggestion_basis");
    }

    public Boolean insertData(String deliveryOrReservation, String foodOrRestaurantSuggestion, String dietPreference,
                              Integer minPrice, Integer maxPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        if(MainActivity.CategoriesDB.getCategories(100) != null)
            values.put("categories_id", 100);

        values.put("delivery_or_reservation", deliveryOrReservation);
        values.put("food_or_restaurant_suggestion", foodOrRestaurantSuggestion);
        values.put("diet_preference", dietPreference);
        values.put("min_price", minPrice);
        values.put("max_price", maxPrice);
        values.put("fixed_diet_id", 1);
        values.put("fixed_price_range_id", 1);

        long result = db.insert("suggestion_basis", null, values);
        if(result == -1) return false;
        else
            return true;
    }

    public SuggestionBasisDataModel getSuggestionBasis(Integer suggestionBasisId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from suggestion_basis where suggestion_basis_id=?",
                new String[] {String.valueOf(suggestionBasisId)});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        String deliveryOrReservation = cursor.getString(1);
        String foodOrRestaurantSuggestion = cursor.getString(2);
        String dietPreference = cursor.getString(3);
        Integer minPrice = cursor.getInt(4);
        Integer maxPrice = cursor.getInt(5);
        Integer categoriesId = cursor.getInt(6);
        CategoriesDataModel categories = MainActivity.CategoriesDB.getCategories(categoriesId);
        DietDataModel fixedDiet = CustomerPreferencesActivity.DietDB.getDiet();
        PriceRangeDataModel fixedPriceRange = CustomerPreferencesActivity.PriceRangeDB.getPriceRange();

        return new SuggestionBasisDataModel(deliveryOrReservation, foodOrRestaurantSuggestion, dietPreference, minPrice,
                maxPrice, categories, fixedDiet, fixedPriceRange);
    }

    public SuggestionBasisDataModel getLastInsertedSuggestionBasis() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from suggestion_basis", null);
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToLast();
        String deliveryOrReservation = cursor.getString(1);
        String foodOrRestaurantSuggestion = cursor.getString(2);
        String dietPreference = cursor.getString(3);
        Integer minPrice = cursor.getInt(4);
        Integer maxPrice = cursor.getInt(5);
        Integer categoriesId = cursor.getInt(6);
        CategoriesDataModel categories = MainActivity.CategoriesDB.getCategories(categoriesId);
        DietDataModel fixedDiet = SwipePreisrahmen.DietDB.getDiet();
        PriceRangeDataModel fixedPriceRange = SwipePreisrahmen.PriceRangeDB.getPriceRange();

        return new SuggestionBasisDataModel(deliveryOrReservation, foodOrRestaurantSuggestion, dietPreference, minPrice,
                maxPrice, categories, fixedDiet, fixedPriceRange);
    }
}
