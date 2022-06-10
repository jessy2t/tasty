package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CustomerPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_uebersicht);

        populateListView();
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
        Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG);
        toast.show();
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