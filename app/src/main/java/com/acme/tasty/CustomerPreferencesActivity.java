package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CustomerPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_uebersicht);

        populateListView();
    }

    public void populateListView(){
        String[] ernaehrungsform = {"vegan", "vegetarisch", "laktosefrei"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                ernaehrungsform);

        ListView list = (ListView) findViewById(R.id.praeferenzen_ernaehrungsweise_liste);
        list.setAdapter(adapter);
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