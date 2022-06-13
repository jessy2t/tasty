package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SwipeEssensort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_essensort);
    }

    public void navigateToSwipeEmpfehlungsart_Zuhause(View view){
        Intent intent = new Intent(this, SwipeEmpfehlungsart.class);
        startActivity(intent);
    }

    public void navigateToSwipeEmpfehlungsart_Restaurant(View view){
        Intent intent = new Intent(this, SwipeEmpfehlungsart.class);
        startActivity(intent);
    }
}