package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.acme.tasty.ui.login.RestaurantLoginActivity;

public class CustomerPreferencesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praeferenzen_uebersicht);
    }

    public void navigateToRestaurantLogin(View view){
        Intent intent = new Intent(this, RestaurantLoginActivity.class);
        startActivity(intent);
    }

    public void navigateToCustomerStart(View view){
        Intent intent = new Intent(this, CustomerStartActivity.class);
        startActivity(intent);
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkbox_indisch:
                if (checked);
            else
                break;
            case R.id.checkbox_mexikanisch:
                if (checked);
            else
                break;
        }
    }
}