package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SwipePreisrahmen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_preisrahmen);
    }

    public void navigateToSwipeErgebnis(View view) {
        Intent intent = new Intent(this, SwipeErgebnis.class);
        startActivity(intent);
    }
}