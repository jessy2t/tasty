package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantRegistration1Activity extends AppCompatActivity {

    protected EditText nameRestaurant;
    protected EditText strasseRestaurant;
    protected EditText hausnummerRestaurant;
    protected EditText plzRestaurant;
    protected EditText ortRestaurant;
    protected CheckBox checkBoxLieferServiceVorhanden;
    protected CheckBox checkBoxReservierungMoeglich;
    protected CheckBox checkBoxReservierungNotwendig;
    protected CheckBox checkBoxInAppBezahlung;
    protected CheckBox checkBoxVegetarisch;
    protected CheckBox checkBoxVegan;
    protected CheckBox checkBoxAsiatisch;
    protected CheckBox checkBoxJapanisch;
    protected CheckBox checkBoxEuropaeisch;
    protected CheckBox checkBoxItalienisch;
    protected CheckBox checkBoxGriechisch;
    protected CheckBox checkBoxMexikanisch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_registration_1);

        nameRestaurant = findViewById(R.id.nameRestaurant);
        strasseRestaurant = findViewById(R.id.strasseRestaurant);
        ortRestaurant = findViewById(R.id.ortRestaurant);
        plzRestaurant = findViewById(R.id.plzRestaurant);
        hausnummerRestaurant = findViewById(R.id.hausnummerRestaurant);

        checkBoxLieferServiceVorhanden = findViewById(R.id.checkBoxLieferServiceVorhanden);
        checkBoxReservierungMoeglich = findViewById(R.id.checkBoxReservierungMoeglich);
        checkBoxReservierungNotwendig = findViewById(R.id.checkBoxReservierungNotwendig);
        checkBoxInAppBezahlung = findViewById(R.id.checkBoxInAppBezahlung);
        checkBoxVegetarisch = findViewById(R.id.checkBoxVegetarisch);
        checkBoxVegan = findViewById(R.id.checkBoxVegan);
        checkBoxAsiatisch = findViewById(R.id.checkBoxAsiatisch);
        checkBoxJapanisch = findViewById(R.id.checkBoxJapanisch);
        checkBoxEuropaeisch = findViewById(R.id.checkBoxEuropaeisch);
        checkBoxItalienisch = findViewById(R.id.checkBoxItalienisch);
        checkBoxGriechisch = findViewById(R.id.checkBoxGriechisch);
        checkBoxMexikanisch = findViewById(R.id.checkBoxMexikanisch);

    }
    public void navigateToRestaurantRegistration2(View view){
        Intent intent = new Intent(this, RestaurantRegistration2Acitivity.class);
        startActivity(intent);
        finish();
    }
}