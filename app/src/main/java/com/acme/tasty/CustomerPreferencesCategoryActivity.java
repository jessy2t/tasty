package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerPreferencesCategoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_kategorie);
    }

    public void kategorie_speichern (View view){
        Intent intent = new Intent(this, CustomerPreferencesActivity.class);
        startActivity(intent);
    }
}
