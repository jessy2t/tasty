package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.acme.tasty.dataModels.DietDataModel;
import com.acme.tasty.databaseHelpers.DietDBHelper;

public class CustomerPreferencesMealtypeActivity extends AppCompatActivity {
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;

    private boolean [] status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_ernaehrungsform);

        getDietPreferences();
    }

    private void getDietPreferences(){
        this.checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        this.checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        this.checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        this.checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        this.checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
        this.checkBox6 = (CheckBox) findViewById(R.id.checkBox6);

        status = new boolean[6];
        DietDataModel diet = CustomerPreferencesActivity.DietDB.getDiet();
        if(diet == null)
            CustomerPreferencesActivity.dietExists = false;
        else {
            CustomerPreferencesActivity.dietExists = true;
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
                checkBox1.setChecked(true);

            checkBox2.setChecked(vegetarisch);
            checkBox3.setChecked(vegan);
            checkBox4.setChecked(glutenfrei);
            checkBox5.setChecked(frutarisch);
            checkBox6.setChecked(laktosefrei);
        }
        this.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAllCheckedChange(isChecked);
            }
        });

        if (checkBox2.isChecked()) {
            checkBox2.setChecked(true);
            status[1] = true;
        }
        if (checkBox3.isChecked()) {
            checkBox3.setChecked(true);
            status[2] = true;
        }
        if (checkBox4.isChecked()) {
            checkBox4.setChecked(true);
            status[3] = true;
        }
        if (checkBox5.isChecked()) {
            checkBox5.setChecked(true);
            status[4] = true;
        }
        if (checkBox6.isChecked()) {
            checkBox6.setChecked(true);
            status[5] = true;
        }
        if (status[1] && status[2] && status[3] && status[4] && status[5]){
            checkBox1.setChecked(true);
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
