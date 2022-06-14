package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SwipeEmpfehlungsart extends AppCompatActivity {
    public static final String FOOD_OR_RESTAURANT_SUGGESTION = "com.acme.tasty.FOOD_OR_RESTAURANT_SUGGESTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_empfehlungsart);
    }

    public void navigateToSwipeBeschraenkungen_Restaurant(View view){
        String deliveryOrReservation = getIntent().getStringExtra(SwipeEssensort.DELIVERY_OR_RESERVATION);

        Intent nextIntent = new Intent(this, SwipeBeschraenkungen.class);
        nextIntent.putExtra(SwipeEssensort.DELIVERY_OR_RESERVATION, deliveryOrReservation);
        nextIntent.putExtra(FOOD_OR_RESTAURANT_SUGGESTION, "restaurant");
        startActivity(nextIntent);
    }

    public void navigateToSwipeBeschraenkungen_Gericht(View view){
        String deliveryOrReservation = getIntent().getStringExtra(SwipeEssensort.DELIVERY_OR_RESERVATION);

        Intent nextIntent = new Intent(this, SwipeBeschraenkungen.class);
        nextIntent.putExtra(SwipeEssensort.DELIVERY_OR_RESERVATION, deliveryOrReservation);
        nextIntent.putExtra(FOOD_OR_RESTAURANT_SUGGESTION, "food");
        startActivity(nextIntent);
    }
}