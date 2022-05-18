package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import com.acme.tasty.ui.login.RestaurantLoginActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.acme.testy.CUSTOMER_MESSAGE";
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