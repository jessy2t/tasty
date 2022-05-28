package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigateToRestaurantLogin(View view){
        Intent intent = new Intent(this, RestaurantLoginActivity.class);
        startActivity(intent);
    }

    public void navigateToCustomerStart(View view){
        Intent intent = new Intent(this, CustomerStartActivity.class);
        startActivity(intent);
    }
}