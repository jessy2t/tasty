package com.acme.tasty.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Base64;

public class RestaurantOwnerDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="restaurant_owner.db";
    public RestaurantOwnerDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table restaurant_owner(owner_id INTEGER primary key autoincrement, username TEXT," +
                "password TEXT, prename TEXT, surname TEXT, phone NUMBER, mail TEXT," +
                "foreign key (username) references restaurant(restaurant_name))");
        String encodedPassword = Base64.getEncoder().encodeToString(("tasty").getBytes());
        insertData("Tony's Tacos", encodedPassword, "Tony", "Stark", "0762112345", "tonystacos@mail.de", db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists restaurant_owner");
    }

    public void clearData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from restaurant_owner");
    }

    public Boolean insertData(String username, String password, String prename, String surname, String phone,
                              String mail, SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);
        values.put("prename", prename);
        values.put("surname", surname);
        values.put("phone", phone);
        values.put("mail", mail);

        long result = db.insert("restaurant_owner", null, values);
        return result != -1;
    }

    public Boolean insertData(String username, String password, String prename, String surname, String phone,
                              String mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);
        values.put("prename", prename);
        values.put("surname", surname);
        values.put("phone", phone);
        values.put("mail", mail);

        long result = db.insert("restaurant_owner", null, values);
        return result != -1;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from restaurant_owner where username=?", new String[] {username});
        boolean userExists = cursor.getCount() > 0;
        cursor.close();

        return userExists;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from restaurant_owner where username=? and password=?", new String[] {username, password});
        boolean credentialsExists = cursor.getCount() > 0;
        cursor.close();

        return credentialsExists;
    }
}
