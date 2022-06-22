package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.dataModels.CategoriesDataModel;

public class CategoriesDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="categories.db";
    public CategoriesDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table categories(categories_id INTEGER primary key, mexican BOOLEAN," +
                "indian BOOLEAN, indonesian BOOLEAN, italian BOOLEAN, german BOOLEAN, american BOOLEAN, chinese BOOLEAN)");
        insertData(true, false, false, false, false, false, false, db);
        insertData(false, false, false, false, false, true, false, db);
        insertData(false, true, false, false, false, false, false, db);
        insertData(false, false, true, false, false, false, false, db);
        insertData(false, false, false, true, false, false, false, db);
        insertData(false, false, false, false, true, false, false, db);
        insertData(false, false, false, false, false, false, true, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists categories");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from categories");
    }

    public Boolean insertData(Boolean mexican, Boolean indian, Boolean indonesian, Boolean italian, Boolean german,
                              Boolean american, Boolean chinese, SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("mexican", mexican);
        values.put("indian", indian);
        values.put("indonesian", indonesian);
        values.put("italian", italian);
        values.put("german", german);
        values.put("american", american);
        values.put("chinese", chinese);

        long result = db.insert("categories", null, values);
        return result != -1;
    }

    public Boolean insertPreferencesData(Boolean mexican, Boolean indian, Boolean indonesian, Boolean italian, Boolean german,
                                         Boolean american, Boolean chinese) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("categories_id", 100);
        values.put("mexican", mexican);
        values.put("indian", indian);
        values.put("indonesian", indonesian);
        values.put("italian", italian);
        values.put("german", german);
        values.put("american", american);
        values.put("chinese", chinese);

        long result = db.insert("categories", null, values);
        return result != -1;
    }

    public Boolean insertData(Boolean mexican, Boolean indian, Boolean indonesian, Boolean italian, Boolean german,
                                        Boolean american, Boolean chinese) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("mexican", mexican);
        values.put("indian", indian);
        values.put("indonesian", indonesian);
        values.put("italian", italian);
        values.put("german", german);
        values.put("american", american);
        values.put("chinese", chinese);

        long result = db.insert("categories", null, values);
        return result != -1;
    }

    public Boolean updateData(Boolean mexican, Boolean indian, Boolean indonesian, Boolean italian, Boolean german,
                              Boolean american, Boolean chinese) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("mexican", mexican);
        values.put("indian", indian);
        values.put("indonesian", indonesian);
        values.put("italian", italian);
        values.put("german", german);
        values.put("american", american);
        values.put("chinese", chinese);

        int result = db.update("categories", values, "categories_id=100", null);
        return result != 0;
    }

    public CategoriesDataModel getCategories(Integer categoriesId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from categories where categories_id=?",
                new String[] {String.valueOf(categoriesId)});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        Boolean mexican = cursor.getInt(1) > 0;
        Boolean indian = cursor.getInt(2) > 0;
        Boolean indonesian = cursor.getInt(3) > 0;
        Boolean italian = cursor.getInt(4) > 0;
        Boolean german = cursor.getInt(5) > 0;
        Boolean american = cursor.getInt(6) > 0;
        Boolean chinese = cursor.getInt(7) > 0;
        cursor.close();

        return new CategoriesDataModel(mexican, indian, indonesian, italian, german, american, chinese);
    }

    public Integer getId(Boolean mexican, Boolean indian, Boolean indonesian, Boolean italian, Boolean german,
                         Boolean american, Boolean chinese) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from categories where mexican=? AND indian=? AND indonesian=? AND italian=? AND german=? AND american=? AND chinese=?",
                new String[] {boolToIntString(mexican), boolToIntString(indian), boolToIntString(indonesian),
                        boolToIntString(italian), boolToIntString(german), boolToIntString(american),
                        boolToIntString(chinese)});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        Integer id = cursor.getInt(0);
        cursor.close();

        return id;
    }

    private String boolToIntString (Boolean bool) {
        if(bool)
            return "1";
        return "0";
    }
}
