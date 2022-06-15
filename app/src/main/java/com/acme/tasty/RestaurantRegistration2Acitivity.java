package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.acme.tasty.databaseHelpers.RestaurantOwnerDBHelper;

public class RestaurantRegistration2Acitivity extends AppCompatActivity {

    protected EditText firstName;
    protected EditText lastName;
    protected EditText phone;
    protected EditText mail;
    protected EditText userName;
    protected EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_registration2);
    }

    public void navigateToRestaurantView(View view){
        firstName = findViewById(R.id.inhaberVorname);
        lastName = findViewById(R.id.inhaberNachname);
        phone = findViewById(R.id.editTextPhone);
        mail = findViewById(R.id.editTextTextEmailAddress);
        userName = findViewById(R.id.username_register);
        password = findViewById(R.id.passwort);
        //String username, String password, String prename, String surname, String phone, String mail
        RestaurantLoginActivity.RestaurantOwnerDB.insertData(userName.getText().toString(),password.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),phone.getText().toString(),mail.getText().toString());

        Intent intent = new Intent(this, CustomerRestaurantOverviewActivity.class);
        startActivity(intent);
        finish();
    }
}