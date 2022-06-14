package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.acme.tasty.databaseHelpers.SuggestionBasisDBHelper;

public class SwipeEssensort extends AppCompatActivity {
    public static final String DELIVERY_OR_RESERVATION = "com.acme.tasty.DELIVERY_OR_RESERVATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_essensort);
    }

    public void navigateToSwipeEmpfehlungsart_Zuhause(View view){
        Intent intent = new Intent(this, SwipeEmpfehlungsart.class);
        intent.putExtra(DELIVERY_OR_RESERVATION, "delivery");
        startActivity(intent);
    }

    public void navigateToSwipeEmpfehlungsart_Restaurant(View view){
        Intent intent = new Intent(this, SwipeEmpfehlungsart.class);
        intent.putExtra(DELIVERY_OR_RESERVATION, "reservation");
        startActivity(intent);
    }
}