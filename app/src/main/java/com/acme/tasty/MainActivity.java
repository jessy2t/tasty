package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.acme.tasty.databaseHelpers.*;


public class MainActivity extends AppCompatActivity {

    public static AddressDBHelper AddressDB;
    public static RestaurantAttributesDBHelper AttributesDB;
    private Toolbar mToolbar;
    public static CityDBHelper CityDB;
    public static CategoriesDBHelper CategoriesDB;
    public static RestaurantDBHelper RestaurantDB;
    public static OpeningHoursDBHelper OpeningHoursDB;
    public static RatingDBHelper RatingDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        createDatabasesConnection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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

    private void createDatabasesConnection() {
        CityDB = new CityDBHelper(this);
        AddressDB = new AddressDBHelper(this);
        CategoriesDB = new CategoriesDBHelper(this);
        AttributesDB = new RestaurantAttributesDBHelper(this);
        RestaurantDB = new RestaurantDBHelper(this);
        OpeningHoursDB = new OpeningHoursDBHelper(this);
        RatingDB = new RatingDBHelper(this);
    }
}