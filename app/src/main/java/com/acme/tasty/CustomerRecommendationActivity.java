package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerRecommendationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_recommendation);
    }

    public void clickOnRestaurantRecommendation(View view) {
        Intent intent = new Intent(this, CustomerRestaurantOverviewActivity.class);
        startActivity(intent);
    }

    public void clickOnReservationOrDelivery(View view) {
        //pop-up with order confirmation
    }

    public void clickOnNewRecommendation(View view) {
        //generate new recommendation
    }

    public void clickOnNewInput(View view) {
        Intent intent = new Intent(this, CustomerSwipeActivity.class);
        startActivity(intent);
    }
}