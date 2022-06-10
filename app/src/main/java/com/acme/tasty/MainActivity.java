package com.acme.tasty;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
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

        //mToolbar = findViewById(R.id.main_toolbar);
        //setSupportActionBar(mToolbar);

        createDatabasesConnection();
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

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkbox_indisch:
                if (checked);
                else
                    break;
            case R.id.checkbox_mexikanisch:
                if (checked);
                else
                    break;
        }
    }
}