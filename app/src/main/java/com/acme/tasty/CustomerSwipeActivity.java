package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

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