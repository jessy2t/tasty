package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.CustomerStartActivity;
import com.acme.tasty.MainActivity;
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
                "categories_id INTEGER, fixed_diet_id INTEGER, fixed_price_range_id INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists price_range");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from price_range");
    }

    public Boolean insertData(String deliveryOrReservation, String foodOrRestaurantSuggestion, String dietPreference,
                              Integer minPrice, Integer maxPrice, Integer categoriesId, Integer fixedDietId,
                              Integer fixedPriceRangeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("delivery_or_reservation", deliveryOrReservation);
        values.put("food_or_restaurant_suggestion", foodOrRestaurantSuggestion);
        values.put("diet_preference", dietPreference);
        values.put("min_price", minPrice);
        values.put("max_price", maxPrice);
        values.put("categories_id", categoriesId);
        values.put("fixed_diet_id", fixedDietId);
        values.put("fixed_price_range_id", fixedPriceRangeId);

        long result = db.insert("price_range", null, values);
        if(result == 1) return false;
        else
            return true;
    }

    public SuggestionBasisDataModel getSuggestionBasis(Integer suggestionBasisId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from price_range where suggestion_basis_id=?",
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
        DietDataModel fixedDiet = CustomerStartActivity.DietDB.getDiet();
        PriceRangeDataModel fixedPriceRange = CustomerStartActivity.PriceRangeDB.getPriceRange();

        return new SuggestionBasisDataModel(deliveryOrReservation, foodOrRestaurantSuggestion, dietPreference, minPrice,
                maxPrice, categories, fixedDiet, fixedPriceRange);
    }
}
