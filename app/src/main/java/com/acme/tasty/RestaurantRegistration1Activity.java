package com.acme.tasty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.acme.tasty.dataModels.CityDataModel;

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
    private Button speichern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_registration1);

        speichern = findViewById(R.id.speichern);
/*
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(nameRestaurant.getText().toString())){
                    nameRestaurant.setError("Email Fehler");
                    return;
                }

                if(TextUtils.isEmpty(straße.getText().toString())){
                    straße.setError("Straße Fehler");
                    return;
                }
                if(TextUtils.isEmpty(hausnummer.getText().toString())){
                    hausnummer.setError("Email Fehler");
                    return;
                }
                if(TextUtils.isEmpty(plz.getText().toString())){
                    plz.setError("PLZ Fehler");
                    return;
                }
            }
        }); */


    }
    public void navigateToRestaurantRegistration2(View view){

        nameRestaurant = findViewById(R.id.nameRestaurant);
        straße = findViewById(R.id.straßeRestaurant);
        hausnummer = findViewById(R.id.hausnummerRestaurant);
        plz = findViewById(R.id.plzRestaurant);
        ort = findViewById(R.id.ortRestaurant);
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



        if(TextUtils.isEmpty(nameRestaurant.getText().toString())){
            nameRestaurant.setError("Bitte Restaurantname eintragen");
            return;
        }
        if(TextUtils.isEmpty(straße.getText().toString())){
            straße.setError("Bitte Straße eintragen");
            return;
        }
        if(TextUtils.isEmpty(hausnummer.getText().toString())){
            hausnummer.setError("Bitte Hausnummer eintragen");
            return;
        }
        if(TextUtils.isEmpty(ort.getText().toString())){
            ort.setError("Bitte Ort eintragen");
            return;
        }
        if(TextUtils.isEmpty(plz.getText().toString())){
            plz.setError("Bitte PLZ eintragen");
            return;
        }
        if(!plz.getText().toString().matches("^[0-9]{5}$")){
            plz.setError("Bitte gültige Postleitzahl eintragen");
            return;
        }

        String zip = plz.getText().toString();
        String city = ort.getText().toString();
        String streetName = straße.getText().toString();
        Integer number = Integer.valueOf(hausnummer.getText().toString());

        if(MainActivity.CityDB.cityExists(zip, city)){
            CityDataModel cityModel = MainActivity.CityDB.getCity(zip);
            if(!cityModel.City.equals(city)) {
                ort.setError("Bitte zur Postleitzahl gültigen Ort eingeben");
                return;
            }
        }
        else
            MainActivity.CityDB.insertData(zip, city);

        Integer addressId = MainActivity.AddressDB.getAddressId(streetName, number, zip);
        if(addressId == null) {
            addressId = MainActivity.AddressDB.insertData(streetName,number,zip);
        }

        Integer catId = MainActivity.CategoriesDB.getId(checkBoxMexikanisch.isChecked(), checkBoxIndisch.isChecked(),
                checkBoxIndonesisch.isChecked(), checkBoxItalienisch.isChecked(), checkBoxDeutsch.isChecked(),
                checkBoxAmerikanisch.isChecked(), checkBoxChinesisch.isChecked());
        if(catId == null){
            MainActivity.CategoriesDB.insertData(checkBoxMexikanisch.isChecked(), checkBoxIndisch.isChecked(),
                    checkBoxIndonesisch.isChecked(), checkBoxItalienisch.isChecked(), checkBoxDeutsch.isChecked(),
                    checkBoxAmerikanisch.isChecked(), checkBoxChinesisch.isChecked());
            catId = MainActivity.CategoriesDB.getId(checkBoxMexikanisch.isChecked(), checkBoxIndisch.isChecked(),
                    checkBoxIndonesisch.isChecked(), checkBoxItalienisch.isChecked(), checkBoxDeutsch.isChecked(),
                    checkBoxAmerikanisch.isChecked(), checkBoxChinesisch.isChecked());
        }

        Integer attributesId = MainActivity.AttributesDB.getId(checkBoxLieferServiceVorhanden.isChecked(),
                checkBoxReservierungMöglich.isChecked(), checkBoxReservierungNotwendig.isChecked(),
                checkBoxInAppBezahlung.isChecked(), checkBoxVegetarisch.isChecked(), checkBoxVegan.isChecked(),
                catId);
        if(attributesId == null) {
            MainActivity.AttributesDB.insertData(checkBoxLieferServiceVorhanden.isChecked(),
                    checkBoxReservierungMöglich.isChecked(),checkBoxReservierungNotwendig.isChecked(),
                    checkBoxInAppBezahlung.isChecked(),checkBoxVegetarisch.isChecked(),checkBoxVegan.isChecked(),catId);
            attributesId = MainActivity.AttributesDB.getId(checkBoxLieferServiceVorhanden.isChecked(),
                    checkBoxReservierungMöglich.isChecked(), checkBoxReservierungNotwendig.isChecked(),
                    checkBoxInAppBezahlung.isChecked(), checkBoxVegetarisch.isChecked(), checkBoxVegan.isChecked(),
                    catId);
        }
        if(MainActivity.RestaurantDB.getRestaurant(nameRestaurant.getText().toString()) != null) {
            nameRestaurant.setError("Restaurant ist bereits registriert");
        }
        else {
            MainActivity.RestaurantDB.insertData(nameRestaurant.getText().toString(), attributesId, addressId);
            Toast.makeText(this, "Restaurant gespeichert", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, RestaurantRegistration2Acitivity.class);
            intent.putExtra(RestaurantLoginActivity.RESTAURANT_USERNAME, nameRestaurant.getText().toString());
            startActivity(intent);
            finish();
        }
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}