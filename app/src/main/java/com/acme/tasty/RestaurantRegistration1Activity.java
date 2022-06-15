package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.acme.tasty.databaseHelpers.AddressDBHelper;
import com.acme.tasty.databaseHelpers.CategoriesDBHelper;
import com.acme.tasty.databaseHelpers.CityDBHelper;
import com.acme.tasty.databaseHelpers.RestaurantAttributesDBHelper;
import com.acme.tasty.databaseHelpers.RestaurantDBHelper;
import com.acme.tasty.databaseHelpers.RestaurantOwnerDBHelper;

public class RestaurantRegistration1Activity extends AppCompatActivity {

    protected EditText nameRestaurant;
    protected EditText straße;
    protected EditText hausnummer;
    protected EditText plz;
    protected EditText ort;
    protected CheckBox checkBoxLieferServiceVorhanden;
    protected CheckBox checkBoxReservierungMöglich;
    protected CheckBox checkBoxReservierungNotwendig;
    protected CheckBox checkBoxInAppBezahlung;
    protected CheckBox checkBoxVegetarisch;
    protected CheckBox checkBoxVegan;
    protected CheckBox checkBoxIndisch;
    protected CheckBox checkBoxIndonesisch;
    protected CheckBox checkBoxItalienisch;
    protected CheckBox checkBoxDeutsch;
    protected CheckBox checkBoxMexikanisch;
    protected CheckBox checkBoxAmerikanisch;
    protected CheckBox checkBoxChinesisch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_registration1);
    }
    public void navigateToRestaurantRegistration2(View view){
        nameRestaurant = findViewById(R.id.nameRestaurant);
        straße = findViewById(R.id.straße);
        hausnummer = findViewById(R.id.hausnummer);
        plz = findViewById(R.id.PLZ);
        ort = findViewById(R.id.ort);
        checkBoxLieferServiceVorhanden = findViewById(R.id.checkBoxLieferServiceVorhanden);
        checkBoxReservierungMöglich = findViewById(R.id.checkBoxReservierungMöglich);
        checkBoxReservierungNotwendig = findViewById(R.id.checkBoxReservierungNotwendig);
        checkBoxInAppBezahlung = findViewById(R.id.checkBoxInAppBezahlung);
        checkBoxVegetarisch = findViewById(R.id.checkBoxVegetarisch);
        checkBoxVegan = findViewById(R.id.checkBoxVegan);
        checkBoxIndisch = findViewById(R.id.checkBoxIndisch);
        checkBoxIndonesisch = findViewById(R.id.checkBoxIndonesisch);
        checkBoxItalienisch = findViewById(R.id.checkBoxItalienisch);
        checkBoxDeutsch = findViewById(R.id.checkBoxDeutsch);
        checkBoxMexikanisch = findViewById(R.id.checkBoxMexikanisch);
        checkBoxAmerikanisch = findViewById(R.id.checkBoxAmerikanisch);
        checkBoxChinesisch = findViewById(R.id.checkBoxChinesisch);

        String zip = plz.getText().toString();
        String city = ort.getText().toString();

        String name = straße.getText().toString();
        Integer number = Integer.valueOf(hausnummer.getText().toString());

        MainActivity.CityDB.insertData(zip, city);

        Integer addressId = MainActivity.AddressDB.insertData(name,number,zip);
        if (addressId == 0)
            return;

        Integer catId = MainActivity.CategoriesDB.getId(
                checkBoxMexikanisch.isChecked(),
                checkBoxIndisch.isChecked(),
                checkBoxIndonesisch.isChecked(),
                checkBoxItalienisch.isChecked(),
                checkBoxDeutsch.isChecked(),
                checkBoxAmerikanisch.isChecked(),
                checkBoxChinesisch.isChecked()
        );

        Integer attributesId = MainActivity.AttributesDB.getId(
                checkBoxLieferServiceVorhanden.isChecked(),
                checkBoxReservierungMöglich.isChecked(),
                checkBoxReservierungNotwendig.isChecked(),
                checkBoxInAppBezahlung.isChecked(),
                checkBoxVegetarisch.isChecked(),
                checkBoxVegan.isChecked(),
                catId
        );

        MainActivity.RestaurantDB.insertData(nameRestaurant.getText().toString(),attributesId,addressId);
        Intent intent = new Intent(this, RestaurantRegistration2Acitivity.class);
        startActivity(intent);
        finish();
    }
}