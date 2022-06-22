package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.acme.tasty.dataModels.OpeningHoursDataModel;

public class OpeningHoursDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="opening_hours.db";

    public OpeningHoursDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table opening_hours(opening_hours_id INTEGER primary key, weekday TEXT," +
                "start_time TEXT, end_time TEXT)");
        insertData("Monday", "11:00", "22:00", db);
        insertData("Saturday", "09:00", "00:00", db);
        insertData("Sunday", "09:00", "00:00", db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists opening_hours");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from opening_hours");
    }

    public Boolean insertData(String weekday, String startTime, String endTime, SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("weekday", weekday);
        values.put("start_time", startTime);
        values.put("end_time", endTime);

        long result = db.insert("opening_hours", null, values);
        return result != -1;
    }

    public OpeningHoursDataModel getOpeningHours(Integer openingHoursId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from opening_hours where opening_hours_id=?",
                new String[] {String.valueOf(openingHoursId)});
        if(cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        String weekday = cursor.getString(1);
        String startTime = cursor.getString(2);
        String endTime = cursor.getString(3);
        cursor.close();

        return new OpeningHoursDataModel(weekday, startTime, endTime);
    }
}
