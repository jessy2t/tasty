package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SwipeBeschraenkungen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_beschraenkungen);
    }

    public void navigateToSwipePreisrahmen_Vegetarisch(View view){
        Intent intent = new Intent(this, SwipePreisrahmen.class);
        startActivity(intent);
    }

    public void navigateToSwipePreisrahmen_Vegan(View view){
        Intent intent = new Intent(this, SwipePreisrahmen.class);
        startActivity(intent);
    }

    public void navigateToSwipePreisrahmen_Fleisch(View view){
        Intent intent = new Intent(this, SwipePreisrahmen.class);
        startActivity(intent);
    }
}