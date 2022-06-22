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
    protected EditText strasse;
    protected EditText hausnummer;
    protected EditText plz;
    protected EditText ort;
    protected CheckBox checkBoxLieferServiceVorhanden;
    protected CheckBox checkBoxReservierungMoeglich;
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
        getUIElements();
    }
    public void navigateToRestaurantRegistration2(View view){
        String zip = plz.getText().toString();
        if(inputFieldsAreEmpty(zip))
            return;

        String streetName = strasse.getText().toString();
        Integer houseNumber = Integer.valueOf(hausnummer.getText().toString());
        Integer addressId = getAddressId(streetName, houseNumber, zip);
        Integer categoriesId = getCategoriesId();
        Integer attributesId = getAttributesId(categoriesId);
        String restaurantNameAsString = nameRestaurant.getText().toString();

        if(MainActivity.RestaurantDB.getRestaurant(restaurantNameAsString) != null) {
            nameRestaurant.setError("Restaurant ist bereits registriert");
        }
        else {
            MainActivity.RestaurantDB.insertData(restaurantNameAsString, attributesId, addressId);
            Toast.makeText(this, "Restaurant gespeichert", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, RestaurantRegistration2Acitivity.class);
            intent.putExtra(RestaurantLoginActivity.RESTAURANT_USERNAME, nameRestaurant.getText().toString());
            startActivity(intent);
            finish();
        }
    }

    private void getUIElements() {
        nameRestaurant = findViewById(R.id.nameRestaurant);
        strasse = findViewById(R.id.straßeRestaurant);
        hausnummer = findViewById(R.id.hausnummerRestaurant);
        plz = findViewById(R.id.plzRestaurant);
        ort = findViewById(R.id.ortRestaurant);
        checkBoxLieferServiceVorhanden = findViewById(R.id.checkBoxLieferServiceVorhanden);
        checkBoxReservierungMoeglich = findViewById(R.id.checkBoxReservierungMöglich);
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
    }

    private Boolean inputFieldsAreEmpty(String zip) {
        boolean result = false;
        if(TextUtils.isEmpty(nameRestaurant.getText().toString())){
            nameRestaurant.setError("Bitte Restaurantname eintragen");
            result = true;
        }
        if(TextUtils.isEmpty(strasse.getText().toString())){
            strasse.setError("Bitte Straße eintragen");
            result = true;
        }
        if(TextUtils.isEmpty(hausnummer.getText().toString())){
            hausnummer.setError("Bitte Hausnummer eintragen");
            result = true;
        }
        if(TextUtils.isEmpty(ort.getText().toString())){
            ort.setError("Bitte Ort eintragen");
            result = true;
        }
        if(TextUtils.isEmpty(plz.getText().toString())){
            plz.setError("Bitte PLZ eintragen");
            result = true;
        }
        if(!plz.getText().toString().matches("^\\d{5}$")){
            plz.setError("Bitte gültige Postleitzahl eintragen");
            result = true;
        }

        String city = ort.getText().toString();
        if(MainActivity.CityDB.cityExists(zip, city)) {
            CityDataModel cityModel = MainActivity.CityDB.getCity(zip);
            if(!cityModel.City.equals(city)) {
                ort.setError("Bitte zur Postleitzahl gültigen Ort eingeben");
                result = true;
            }
        }
        else
            MainActivity.CityDB.insertData(zip, city);

        return result;
    }

    private Integer getAddressId(String streetName, Integer houseNumber, String zip) {
        Integer addressId = MainActivity.AddressDB.getAddressId(streetName, houseNumber, zip);
        if(addressId == null) {
            addressId = MainActivity.AddressDB.insertData(streetName,houseNumber,zip);
        }
        return addressId;
    }

    private Integer getCategoriesId() {
        Integer catId = MainActivity.CategoriesDB
                .getId(checkBoxMexikanisch.isChecked(), checkBoxIndisch.isChecked(), checkBoxIndonesisch.isChecked(),
                        checkBoxItalienisch.isChecked(), checkBoxDeutsch.isChecked(), checkBoxAmerikanisch.isChecked(),
                        checkBoxChinesisch.isChecked());

        if(catId == null) {
            MainActivity.CategoriesDB.insertData(checkBoxMexikanisch.isChecked(), checkBoxIndisch.isChecked(),
                    checkBoxIndonesisch.isChecked(), checkBoxItalienisch.isChecked(), checkBoxDeutsch.isChecked(),
                    checkBoxAmerikanisch.isChecked(), checkBoxChinesisch.isChecked());
            catId = MainActivity.CategoriesDB.getId(checkBoxMexikanisch.isChecked(), checkBoxIndisch.isChecked(),
                    checkBoxIndonesisch.isChecked(), checkBoxItalienisch.isChecked(), checkBoxDeutsch.isChecked(),
                    checkBoxAmerikanisch.isChecked(), checkBoxChinesisch.isChecked());
        }

        return catId;
    }

    private Integer getAttributesId(Integer categoriesId) {
        Integer attributesId = MainActivity.AttributesDB.getId(checkBoxLieferServiceVorhanden.isChecked(),
                checkBoxReservierungMoeglich.isChecked(), checkBoxReservierungNotwendig.isChecked(),
                checkBoxInAppBezahlung.isChecked(), checkBoxVegetarisch.isChecked(), checkBoxVegan.isChecked(),
                categoriesId);
        if(attributesId == null) {
            MainActivity.AttributesDB.insertData(checkBoxLieferServiceVorhanden.isChecked(),
                    checkBoxReservierungMoeglich.isChecked(), checkBoxReservierungNotwendig.isChecked(),
                    checkBoxInAppBezahlung.isChecked(), checkBoxVegetarisch.isChecked(), checkBoxVegan.isChecked(), categoriesId);
            attributesId = MainActivity.AttributesDB.getId(checkBoxLieferServiceVorhanden.isChecked(),
                    checkBoxReservierungMoeglich.isChecked(), checkBoxReservierungNotwendig.isChecked(),
                    checkBoxInAppBezahlung.isChecked(), checkBoxVegetarisch.isChecked(), checkBoxVegan.isChecked(),
                    categoriesId);
        }
        return attributesId;
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}