package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.acme.tasty.dataModels.CategoriesDataModel;

public class CustomerPreferencesCategoryActivity extends AppCompatActivity {
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_kategorie);

        Toolbar toolbar = findViewById(R.id.preferences_kategorie_toolbar);
        setSupportActionBar(toolbar);

        getCategoryPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void getCategoryPreferences(){
        this.checkBox1 = findViewById(R.id.checkBoxKat1);
        this.checkBox2 = findViewById(R.id.checkBoxKat2);
        this.checkBox3 = findViewById(R.id.checkBoxKat3);
        this.checkBox4 = findViewById(R.id.checkBoxKat4);
        this.checkBox5 = findViewById(R.id.checkBoxKat5);
        this.checkBox6 = findViewById(R.id.checkBoxKat6);
        this.checkBox7 = findViewById(R.id.checkBoxKat7);

        CategoriesDataModel categories = MainActivity.CategoriesDB.getCategories(100);
        if(categories == null)
            CustomerPreferencesActivity.categoryExists = false;
        else {
            CustomerPreferencesActivity.categoryExists = true;
            boolean indian = false;
            boolean mexican = false;
            boolean american = false;
            boolean chinese = false;
            boolean german = false;
            boolean italian = false;

            if(categories.Indian)
                indian = true;
            if(categories.Mexican)
                mexican = true;
            if(categories.American)
                american = true;
            if(categories.Chinese)
                chinese = true;
            if(categories.German)
                german = true;
            if(categories.Italian)
                italian = true;

            if(indian && mexican && american && chinese && german && italian)
                checkBox1.setChecked(true);

            checkBox2.setChecked(indian);
            checkBox3.setChecked(mexican);
            checkBox4.setChecked(american);
            checkBox5.setChecked(chinese);
            checkBox6.setChecked(german);
            checkBox7.setChecked(italian);
        }

        this.checkBox1.setOnCheckedChangeListener((buttonView, isChecked)-> checkAllCheckedChange(isChecked));
        this.checkBox2.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox2, isChecked));
        this.checkBox3.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox3, isChecked));
        this.checkBox4.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox4, isChecked));
        this.checkBox5.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox5, isChecked));
        this.checkBox6.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox6, isChecked));
        this.checkBox7.setOnCheckedChangeListener((buttonView, isChecked)-> checkOneCheckedChange(checkBox7, isChecked));
    }

    private  void checkOneCheckedChange(CheckBox checkBox, boolean isChecked) {

        if (!isChecked) {
            if (checkBox1.isChecked()) {
                this.checkBox1.setChecked(false);

                if (checkBox.getId() == checkBox2.getId()){
                    this.checkBox2.setChecked(false);
                    this.checkBox3.setChecked(true);
                    this.checkBox4.setChecked(true);
                    this.checkBox5.setChecked(true);
                    this.checkBox6.setChecked(true);
                    this.checkBox7.setChecked(true);
                }
                if (checkBox.getId() == checkBox3.getId()){
                    this.checkBox2.setChecked(true);
                    this.checkBox3.setChecked(false);
                    this.checkBox4.setChecked(true);
                    this.checkBox5.setChecked(true);
                    this.checkBox6.setChecked(true);
                    this.checkBox7.setChecked(true);
                }
                if (checkBox.getId() == checkBox4.getId()){
                    this.checkBox2.setChecked(true);
                    this.checkBox3.setChecked(true);
                    this.checkBox4.setChecked(false);
                    this.checkBox5.setChecked(true);
                    this.checkBox6.setChecked(true);
                    this.checkBox7.setChecked(true);
                }
                if (checkBox.getId() == checkBox5.getId()){
                    this.checkBox2.setChecked(true);
                    this.checkBox3.setChecked(true);
                    this.checkBox4.setChecked(true);
                    this.checkBox5.setChecked(false);
                    this.checkBox6.setChecked(true);
                    this.checkBox7.setChecked(true);
                }
                if (checkBox.getId() == checkBox6.getId()){
                    this.checkBox2.setChecked(true);
                    this.checkBox3.setChecked(true);
                    this.checkBox4.setChecked(true);
                    this.checkBox5.setChecked(true);
                    this.checkBox6.setChecked(false);
                    this.checkBox7.setChecked(true);
                }
                if (checkBox.getId() == checkBox7.getId()){
                    this.checkBox2.setChecked(true);
                    this.checkBox3.setChecked(true);
                    this.checkBox4.setChecked(true);
                    this.checkBox5.setChecked(true);
                    this.checkBox6.setChecked(true);
                    this.checkBox7.setChecked(false);
                }
            }
        }else if(checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()
            && checkBox5.isChecked() && checkBox6.isChecked() && checkBox7.isChecked()) {
            this.checkBox1.setChecked(true);
        }
    }

    private void checkAllCheckedChange(boolean isChecked)  {
        this.checkBox1.setChecked(isChecked);
        this.checkBox2.setChecked(isChecked);
        this.checkBox3.setChecked(isChecked);
        this.checkBox4.setChecked(isChecked);
        this.checkBox5.setChecked(isChecked);
        this.checkBox6.setChecked(isChecked);
        this.checkBox7.setChecked(isChecked);
    }

    public void kategorie_speichern (View view){
        boolean indian = false;
        boolean mexican = false;
        boolean american = false;
        boolean chinese = false;
        boolean german = false;
        boolean italian = false;

        if (((CheckBox)findViewById(R.id.checkBoxKat1)).isChecked()){
            indian = true;
            mexican = true;
            american = true;
            chinese = true;
            german = true;
            italian = true;
        }

        else {
            if (((CheckBox)findViewById(R.id.checkBoxKat2)).isChecked())
                indian = true;

            if (((CheckBox)findViewById(R.id.checkBoxKat3)).isChecked())
                mexican = true;

            if (((CheckBox)findViewById(R.id.checkBoxKat4)).isChecked())
                american = true;

            if (((CheckBox)findViewById(R.id.checkBoxKat5)).isChecked())
                chinese = true;

            if (((CheckBox)findViewById(R.id.checkBoxKat6)).isChecked())
                german = true;

            if (((CheckBox)findViewById(R.id.checkBoxKat7)).isChecked())
                italian = true;
        }

        if(insertOrUpdateCategoriesDB(indian, mexican, american, chinese, german, italian))
            navigateToPreferencesOverview();
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Fehler bei der Speicherung. Bitte kontaktieren Sie den Kundendienst.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private Boolean insertOrUpdateCategoriesDB(Boolean indian, Boolean mexican, Boolean american, Boolean chinese,
                                            Boolean german, Boolean italian){
        if(CustomerPreferencesActivity.categoryExists)
            return MainActivity.CategoriesDB.updateData(mexican, indian, false, italian, german,
                    american, chinese);

        return MainActivity.CategoriesDB.insertPreferencesData(mexican, indian, false, italian, german,
                american, chinese);
    }

    private void navigateToPreferencesOverview(){
        Intent intent = new Intent(this, CustomerPreferencesActivity.class);
        Toast toast = Toast.makeText(getApplicationContext(), "Ihre Kategorie-Präferenzen wurden gespeichert.", Toast.LENGTH_LONG);
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
