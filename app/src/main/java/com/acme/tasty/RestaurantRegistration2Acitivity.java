package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RestaurantRegistration2Acitivity extends AppCompatActivity {

    protected EditText inhaberVorname;
    protected EditText inhaberNachname;
    protected EditText passwort;
    protected EditText passwortWdh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_registration_2);

        inhaberVorname = findViewById(R.id.inhaberVorname);
        inhaberNachname = findViewById(R.id.inhaberNachname);
        passwort = findViewById(R.id.passwort);
        passwortWdh = findViewById(R.id.passwortWdh);
    }
}