package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerPreferencesCategoryActivity extends AppCompatActivity {
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;

    private boolean [] status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_kategorie);

        this.checkBox1 = (CheckBox) findViewById(R.id.checkBoxKat1);
        this.checkBox2 = (CheckBox) findViewById(R.id.checkBoxKat2);
        this.checkBox3 = (CheckBox) findViewById(R.id.checkBoxKat3);
        this.checkBox4 = (CheckBox) findViewById(R.id.checkBoxKat4);
        this.checkBox5 = (CheckBox) findViewById(R.id.checkBoxKat5);
        this.checkBox6 = (CheckBox) findViewById(R.id.checkBoxKat6);
        this.checkBox7 = (CheckBox) findViewById(R.id.checkBoxKat7);

        status = new boolean[7];

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
        if (checkBox7.isChecked()) {
            checkBox7.setChecked(true);
            status[6] = true;
        }
        if (status[1] && status[2] && status[3] && status[4] && status[5] && status[6] == true){
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
        this.checkBox7.setChecked(isChecked);
    }

    public void kategorie_speichern (View view){
        Intent intent = new Intent(this, CustomerPreferencesActivity.class);
        Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG);
        toast.show();
        startActivity(intent);
    }
}
