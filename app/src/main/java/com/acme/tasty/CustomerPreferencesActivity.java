package com.acme.tasty;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.acme.tasty.dataModels.PriceRangeDataModel;
import com.acme.tasty.databaseHelpers.DietDBHelper;
import com.acme.tasty.databaseHelpers.PriceRangeDBHelper;

public class CustomerPreferencesActivity extends AppCompatActivity {
    public static PriceRangeDBHelper PriceRangeDB;
    public static DietDBHelper DietDB;
    private Boolean priceRangeExists;
    public static Boolean dietExists;
    public static Boolean categoryExists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_uebersicht);

        PriceRangeDB = new PriceRangeDBHelper(this);
        DietDB = new DietDBHelper(this);

        getPriceRangeValues();
        populateListView();
    }

    private void getPriceRangeValues() {
        PriceRangeDataModel priceRange = PriceRangeDB.getPriceRange();

        if(priceRange != null) {
            priceRangeExists = true;
            ((EditText)findViewById(R.id.lowerNumber)).setText(String.valueOf(priceRange.MinPrice));
            ((EditText)findViewById(R.id.higherNumber)).setText(String.valueOf(priceRange.MaxPrice));
        }
        else
            priceRangeExists = false;
    }

    public void populateListView(){
        String[] ernaehrungsform = {"vegan", "vegetarisch", "laktosefrei"};
        String[] kategorie = {"mexikanisch", "chinesich", "italienisch"};

        ArrayAdapter<String> ernaehrungsformAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                ernaehrungsform);

        ArrayAdapter<String> kategorieAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                kategorie);

        ListView ernaehrungsformList = (ListView) findViewById(R.id.praeferenzen_ernaehrungsweise_liste);
        ernaehrungsformList.setAdapter(ernaehrungsformAdapter);

        ListView kategorieList = (ListView) findViewById(R.id.praeferenzen_kategorie_liste);
        kategorieList.setAdapter(kategorieAdapter);
    }

    public void saveToPreferencesPrice(View v){
        String minPriceStringValue = ((EditText)findViewById(R.id.lowerNumber)).getText().toString();
        String maxPriceStringValue = ((EditText)findViewById(R.id.higherNumber)).getText().toString();

        if(minPriceStringValue.isEmpty() || maxPriceStringValue.isEmpty())
            showValidationMessage("Geben Sie einen gültigen Mindest- und Höchstpreis an.");

        else {
            Integer minPriceIntValue = Integer.valueOf(minPriceStringValue);
            Integer maxPriceIntValue = Integer.valueOf(maxPriceStringValue);
            if(minPriceIntValue >= maxPriceIntValue)
                showValidationMessage("Der Mindestpreis muss kleiner als der Höchstpreis sein.");

            else {
                Boolean successfullInsertOrUpdate = insertOrUpdatePriceRangeDB(minPriceIntValue, maxPriceIntValue);
                if(successfullInsertOrUpdate)
                    showValidationMessage("Ihre Preispräferenzen wurden gespeichert.");
                else
                    showValidationMessage("Fehler bei der Speicherung. Bitte kontaktieren Sie den Kundendienst.");
            }
        }
    }

    private void showValidationMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    private Boolean insertOrUpdatePriceRangeDB(Integer minPrice, Integer maxPrice) {
        if(priceRangeExists)
            return PriceRangeDB.updateData(minPrice, maxPrice);

        return PriceRangeDB.insertData(minPrice, maxPrice);
    }

    public void navigateToPreferencesCategory(View v){
        Intent intent = new Intent(this, CustomerPreferencesCategoryActivity.class);
        startActivity(intent);
    }

    public void navigateToPreferencesMealtype(View v){
        Intent intent = new Intent(this, CustomerPreferencesMealtypeActivity.class);
        startActivity(intent);
    }
}