package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantRegistration2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_registration2);
    }
    public void navigateToRestaurantRegistration3(View view){
        Intent intent = new Intent(this, RestaurantRegistration3Acitivity.class);
        startActivity(intent);
        finish();
    }
}