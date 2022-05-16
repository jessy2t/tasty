package com.acme.tasty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addOnClickListenerToButton(R.id.restaurant_btn);
        addOnClickListenerToButton(R.id.customer_btn);
    }

    private void addOnClickListenerToButton(int id) {
        Button button = findViewById(id);
        button.setOnClickListener(v -> button.setText("Clicked!"));
    }
}