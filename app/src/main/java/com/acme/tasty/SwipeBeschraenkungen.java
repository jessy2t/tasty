package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SwipeBeschraenkungen extends AppCompatActivity {

    public static final String DIET_PREFERENCE = "com.acme.tasty.DIET_PREFERENCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_beschraenkungen);
    }

    public void navigateToSwipePreisrahmen_Vegetarisch(View view){
        String deliveryOrReservation = getIntent().getStringExtra(SwipeEssensort.DELIVERY_OR_RESERVATION);
        String foodOrRestaurantSuggestion = getIntent().getStringExtra(SwipeEmpfehlungsart.FOOD_OR_RESTAURANT_SUGGESTION);

        Intent nextIntent = new Intent(this, SwipePreisrahmen.class);
        nextIntent.putExtra(SwipeEssensort.DELIVERY_OR_RESERVATION, deliveryOrReservation);
        nextIntent.putExtra(SwipeEmpfehlungsart.FOOD_OR_RESTAURANT_SUGGESTION, foodOrRestaurantSuggestion);
        nextIntent.putExtra(DIET_PREFERENCE, "vegetarian");
        startActivity(nextIntent);
    }

    public void navigateToSwipePreisrahmen_Vegan(View view){
        String deliveryOrReservation = getIntent().getStringExtra(SwipeEssensort.DELIVERY_OR_RESERVATION);
        String foodOrRestaurantSuggestion = getIntent().getStringExtra(SwipeEmpfehlungsart.FOOD_OR_RESTAURANT_SUGGESTION);

        Intent nextIntent = new Intent(this, SwipePreisrahmen.class);
        nextIntent.putExtra(SwipeEssensort.DELIVERY_OR_RESERVATION, deliveryOrReservation);
        nextIntent.putExtra(SwipeEmpfehlungsart.FOOD_OR_RESTAURANT_SUGGESTION, foodOrRestaurantSuggestion);
        nextIntent.putExtra(DIET_PREFERENCE, "vegan");
        startActivity(nextIntent);
    }

    public void navigateToSwipePreisrahmen_Fleisch(View view){
        String deliveryOrReservation = getIntent().getStringExtra(SwipeEssensort.DELIVERY_OR_RESERVATION);
        String foodOrRestaurantSuggestion = getIntent().getStringExtra(SwipeEmpfehlungsart.FOOD_OR_RESTAURANT_SUGGESTION);

        Intent nextIntent = new Intent(this, SwipePreisrahmen.class);
        nextIntent.putExtra(SwipeEssensort.DELIVERY_OR_RESERVATION, deliveryOrReservation);
        nextIntent.putExtra(SwipeEmpfehlungsart.FOOD_OR_RESTAURANT_SUGGESTION, foodOrRestaurantSuggestion);
        nextIntent.putExtra(DIET_PREFERENCE, "carnivore");
        startActivity(nextIntent);
    }
}