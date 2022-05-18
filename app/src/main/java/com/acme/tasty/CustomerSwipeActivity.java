package com.acme.tasty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CustomerSwipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_swipe);
    }

    public void onClickAtHome(View view) {
        navigateToRecommendation();
    }

    public void onClickRestaurant(View view) {
        navigateToRecommendation();
    }

    private void navigateToRecommendation(){
        Intent intent = new Intent(this, CustomerRecommendationActivity.class);
        startActivity(intent);
    }
}