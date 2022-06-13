package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SwipeEmpfehlungsart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_empfehlungsart);
    }

    public void navigateToSwipeBeschraenkungen_Restaurant(View view){
        Intent intent = new Intent(this, SwipeBeschraenkungen.class);
        startActivity(intent);
    }

    public void navigateToSwipeBeschraenkungen_Gericht(View view){
        Intent intent = new Intent(this, SwipeBeschraenkungen.class);
        startActivity(intent);
    }
}