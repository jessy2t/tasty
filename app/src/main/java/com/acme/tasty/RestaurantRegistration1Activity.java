package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantRegistration1Activity extends AppCompatActivity {

    protected static TextView nameRestaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_registration1);

        nameRestaurant = findViewById(R.id.nameRestaurant);
    }
    public void navigateToRestaurantRegistration2(View view){
        Intent intent = new Intent(this, RestaurantRegistration2Acitivity.class);
        startActivity(intent);
        finish();
    }
}