package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.acme.tasty.dataModels.DietDataModel;
import com.acme.tasty.databaseHelpers.DietDBHelper;

public class CustomerPreferencesMealtypeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_ernaehrungsform);

        getDietPreferences();
    }

    private void getDietPreferences(){
        DietDataModel diet = CustomerPreferencesActivity.DietDB.getDiet();
        if(diet == null)
            CustomerPreferencesActivity.dietExists = false;
        else {
            CustomerPreferencesActivity.dietExists = true;
            CheckBox allesCheckBox = findViewById(R.id.checkBox1);
            CheckBox vegetarischCheckBox = findViewById(R.id.checkBox2);
            CheckBox veganCheckBox = findViewById(R.id.checkBox3);
            CheckBox glutenfreiCheckBox = findViewById(R.id.checkBox4);
            CheckBox frutarischCheckBox = findViewById(R.id.checkBox5);
            CheckBox laktosefreiCheckBox = findViewById(R.id.checkBox6);

            Boolean vegetarisch = false;
            Boolean vegan = false;
            Boolean glutenfrei = false;
            Boolean frutarisch = false;
            Boolean laktosefrei = false;

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
                allesCheckBox.setChecked(true);

            vegetarischCheckBox.setChecked(vegetarisch);
            veganCheckBox.setChecked(vegan);
            glutenfreiCheckBox.setChecked(glutenfrei);
            frutarischCheckBox.setChecked(frutarisch);
            laktosefreiCheckBox.setChecked(laktosefrei);
        }
    }

    public void ernaehrungsweise_speichern (View view){
        Boolean vegetarisch = false;
        Boolean vegan = false;
        Boolean glutenfrei = false;
        Boolean frutarisch = false;
        Boolean laktosefrei = false;

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
}
