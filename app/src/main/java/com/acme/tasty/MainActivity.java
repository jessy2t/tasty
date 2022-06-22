package com.acme.tasty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.acme.tasty.databaseHelpers.*;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static AddressDBHelper AddressDB;
    @SuppressLint("StaticFieldLeak")
    public static RestaurantAttributesDBHelper AttributesDB;
    public static CityDBHelper CityDB;
    public static CategoriesDBHelper CategoriesDB;
    @SuppressLint("StaticFieldLeak")
    public static RestaurantDBHelper RestaurantDB;
    @SuppressLint("StaticFieldLeak")
    public static OpeningHoursDBHelper OpeningHoursDB;
    @SuppressLint("StaticFieldLeak")
    public static RatingDBHelper RatingDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDatabasesConnection();
    }

    private void createDatabasesConnection() {
        CityDB = new CityDBHelper(this);
        AddressDB = new AddressDBHelper(this);
        CategoriesDB = new CategoriesDBHelper(this);
        AttributesDB = new RestaurantAttributesDBHelper(this);
        RestaurantDB = new RestaurantDBHelper(this);
        OpeningHoursDB = new OpeningHoursDBHelper(this);
        RatingDB = new RatingDBHelper(this);
    }

    public void navigateToRestaurantLogin(View view){
        Intent intent = new Intent(this, RestaurantLoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void navigateToCustomerStart(View view){
        Intent intent = new Intent(this, CustomerStartActivity.class);
        startActivity(intent);
        finish();
    }
}