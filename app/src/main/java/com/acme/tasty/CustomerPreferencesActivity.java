package com.acme.tasty;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.acme.tasty.dataModels.CategoriesDataModel;
import com.acme.tasty.dataModels.DietDataModel;
import com.acme.tasty.dataModels.PriceRangeDataModel;
import com.acme.tasty.databaseHelpers.DietDBHelper;
import com.acme.tasty.databaseHelpers.PriceRangeDBHelper;

import java.util.ArrayList;

public class CustomerPreferencesActivity extends AppCompatActivity {
    public static PriceRangeDBHelper PriceRangeDB;
    public static DietDBHelper DietDB;
    private Boolean priceRangeExists;
    public static Boolean dietExists;
    public static Boolean categoryExists;
    private ArrayList<String> ernaehrungsform;
    private ArrayList<String> kategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_uebersicht);

        PriceRangeDB = new PriceRangeDBHelper(this);
        DietDB = new DietDBHelper(this);

        Toolbar toolbar = findViewById(R.id.preferences_toolbar);
        setSupportActionBar(toolbar);

        getPriceRangeValues();
        populateListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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
        ernaehrungsform = new ArrayList<>();
        kategorie = new ArrayList<>();

        DietDataModel diet = CustomerPreferencesActivity.DietDB.getDiet();
        if(diet == null)
            CustomerPreferencesActivity.dietExists = false;
        else {
            CustomerPreferencesActivity.dietExists = true;

            if (diet.Vegetarian)
                ernaehrungsform.add("vergetarisch");
            if (diet.Vegan)
                ernaehrungsform.add("vegan");
            if (diet.Glutenfree)
                ernaehrungsform.add("glutenfrei");
            if (diet.Fruitarian)
                ernaehrungsform.add("frutarisch");
            if (diet.Lactosefree)
                ernaehrungsform.add("laktosefrei");
        }

        CategoriesDataModel categories = MainActivity.CategoriesDB.getCategories(100);
        if(categories == null)
            CustomerPreferencesActivity.categoryExists = false;
        else {
            CustomerPreferencesActivity.categoryExists = true;

            if (categories.Indian)
                kategorie.add("indisch");
            if (categories.Mexican)
                kategorie.add("mexicanisch");
            if (categories.American)
                kategorie.add("amerikanisch");
            if (categories.Chinese)
                kategorie.add("chinesich");
            if (categories.German)
                kategorie.add("deutsch");
            if (categories.Italian)
                kategorie.add("italienisch");
        }
        makeListView();
    }

    public void makeListView(){
        //Adapter für ernaehrungsform
        ArrayAdapter<String> ernaehrungsformAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                ernaehrungsform);

        ListView ernaehrungsformList = findViewById(R.id.praeferenzen_ernaehrungsweise_liste);
        ernaehrungsformList.setAdapter(ernaehrungsformAdapter);

        //Adapter für kategorie
        ArrayAdapter<String> kategorieAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                kategorie);
        ListView kategorieList = findViewById(R.id.praeferenzen_kategorie_liste);
        kategorieList.setAdapter(kategorieAdapter);

        setRightHeight(ernaehrungsformAdapter, ernaehrungsformList);
        setRightHeight(kategorieAdapter, kategorieList);
    }

    public void setRightHeight(ArrayAdapter aa, ListView lv ){
        int totalehight= 0;
        for (int i = 0; i < aa.getCount(); i++) {
            View listItemErnaehrungsform = aa.getView(i, null, lv);
            listItemErnaehrungsform.measure(0, 0);
            totalehight += listItemErnaehrungsform.getMeasuredHeight();
            ViewGroup.LayoutParams paramse = lv.getLayoutParams();
            paramse.height = totalehight + (lv.getDividerHeight() * (aa.getCount() - 1));
            lv.setLayoutParams(paramse);
            lv.requestLayout();
        }
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

    public void navigateToHome(MenuItem item){
        Intent intent = new Intent(this, CustomerStartActivity.class);
        startActivity(intent);
    }

    public void navigateToPreferences(MenuItem item){
        Intent intent = new Intent(this, CustomerPreferencesActivity.class);
        startActivity(intent);
    }
}