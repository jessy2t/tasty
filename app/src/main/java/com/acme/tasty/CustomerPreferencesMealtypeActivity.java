package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.acme.tasty.dataModels.DietDataModel;
import com.acme.tasty.databaseHelpers.DietDBHelper;

public class CustomerPreferencesMealtypeActivity extends AppCompatActivity {
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_ernaehrungsform);

        Toolbar toolbar = findViewById(R.id.preferences_ernaehrungsform_toolbar);
        setSupportActionBar(toolbar);

        getDietPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void getDietPreferences(){
        this.checkBox1 = findViewById(R.id.checkBox1);
        this.checkBox2 = findViewById(R.id.checkBox2);
        this.checkBox3 = findViewById(R.id.checkBox3);
        this.checkBox4 = findViewById(R.id.checkBox4);
        this.checkBox5 = findViewById(R.id.checkBox5);
        this.checkBox6 = findViewById(R.id.checkBox6);

        DietDataModel diet = CustomerPreferencesActivity.DietDB.getDiet();
        if(diet == null)
            CustomerPreferencesActivity.dietExists = false;
        else {
            CustomerPreferencesActivity.dietExists = true;
            boolean vegetarisch = false;
            boolean vegan = false;
            boolean glutenfrei = false;
            boolean frutarisch = false;
            boolean laktosefrei = false;

            if(diet.Vegetarian)
                vegetarisch = true;
            if(diet.Vegan)
                vegan = true;
            if(diet.Glutenfree)
                glutenfrei = true;
            if(diet.Fruitarian)
                frutarisch = true;
            if(diet.Lactosefree)
                laktosefrei = true;

            if(vegetarisch && vegan && glutenfrei && frutarisch && laktosefrei)
                checkBox1.setChecked(true);

            checkBox2.setChecked(vegetarisch);
            checkBox3.setChecked(vegan);
            checkBox4.setChecked(glutenfrei);
            checkBox5.setChecked(frutarisch);
            checkBox6.setChecked(laktosefrei);
        }
        this.checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> checkAllCheckedChange(isChecked));
        this.checkBox2.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox2, isChecked));
        this.checkBox3.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox3, isChecked));
        this.checkBox4.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox4, isChecked));
        this.checkBox5.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox5, isChecked));
        this.checkBox6.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox6, isChecked));
    }

    private  void checkOneCheckedChange(CheckBox checkBox, boolean isChecked) {

        if (isChecked == false) {
            if (checkBox1.isChecked() == true) {
                this.checkBox1.setChecked(false);
                this.checkBox2.setChecked(true);
                this.checkBox3.setChecked(true);
                this.checkBox4.setChecked(true);
                this.checkBox5.setChecked(true);
                this.checkBox6.setChecked(true);
            }
        }
        if (checkBox.getId() == checkBox2.getId()){
            this.checkBox2.setChecked(isChecked);
        }
        if (checkBox.getId() == checkBox3.getId()){
            this.checkBox3.setChecked(isChecked);
        }
        if (checkBox.getId() == checkBox4.getId()){
            this.checkBox4.setChecked(isChecked);
        }
        if (checkBox.getId() == checkBox5.getId()){
            this.checkBox5.setChecked(isChecked);
        }
        if (checkBox.getId() == checkBox6.getId()){
            this.checkBox6.setChecked(isChecked);
        }
        if (isChecked & (checkBox2.isChecked() & (checkBox3.isChecked() & (checkBox4.isChecked() & (checkBox5.isChecked() & (checkBox6.isChecked())))))) {
            this.checkBox1.setChecked(true);
            Toast toast = Toast.makeText(getApplicationContext(), "Stimmt", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void checkAllCheckedChange(boolean isChecked)  {
        this.checkBox1.setChecked(isChecked);
        this.checkBox2.setChecked(isChecked);
        this.checkBox3.setChecked(isChecked);
        this.checkBox4.setChecked(isChecked);
        this.checkBox5.setChecked(isChecked);
        this.checkBox6.setChecked(isChecked);
    }

    public void ernaehrungsweise_speichern (View view){
        boolean vegetarisch = false;
        boolean vegan = false;
        boolean glutenfrei = false;
        boolean frutarisch = false;
        boolean laktosefrei = false;

        if (((CheckBox)findViewById(R.id.checkBox1)).isChecked()){
            vegetarisch = true;
            vegan = true;
            glutenfrei = true;
            frutarisch = true;
            laktosefrei = true;
        }

        else {
            if (((CheckBox)findViewById(R.id.checkBox2)).isChecked())
                vegetarisch = true;

            if (((CheckBox)findViewById(R.id.checkBox3)).isChecked())
                vegan = true;

            if (((CheckBox)findViewById(R.id.checkBox4)).isChecked())
                glutenfrei = true;

            if (((CheckBox)findViewById(R.id.checkBox5)).isChecked())
                frutarisch = true;

            if (((CheckBox)findViewById(R.id.checkBox6)).isChecked())
                laktosefrei = true;
        }

        if(insertOrUpdateDietDB(vegetarisch, vegan, glutenfrei, laktosefrei, frutarisch))
            navigateToPreferencesOverview();
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Fehler bei der Speicherung. Bitte kontaktieren Sie den Kundendienst.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private Boolean insertOrUpdateDietDB(Boolean vegetarian, Boolean vegan, Boolean glutenfree,
                                         Boolean lactosefree, Boolean fruitarian) {
        if(CustomerPreferencesActivity.dietExists)
            return CustomerPreferencesActivity.DietDB.updateData(vegetarian, vegan, false, glutenfree,
                    lactosefree, fruitarian);
        return CustomerPreferencesActivity.DietDB.insertData(vegetarian, vegan, false, glutenfree, lactosefree,
                fruitarian);
    }

    private void navigateToPreferencesOverview() {
        Intent intent = new Intent(this, CustomerPreferencesActivity.class);
        Toast toast = Toast.makeText(getApplicationContext(), "Ihre Ernährungsweise wurde gespeichert.", Toast.LENGTH_LONG);
        toast.show();
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
