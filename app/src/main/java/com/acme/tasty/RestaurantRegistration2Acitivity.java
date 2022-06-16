package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
    protected EditText passwordWdh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_registration2);
    }

    public void navigateToRestaurantOverviewRegistration(View view){

        firstName = findViewById(R.id.inhaberVorname);
        lastName = findViewById(R.id.inhaberNachname);
        phone = findViewById(R.id.inhaberTelefonnummer);
        mail = findViewById(R.id.inhaberEmail);
        userName = findViewById(R.id.username_register);
        password = findViewById(R.id.passwort);
        passwordWdh = findViewById(R.id.passwortWdh);

        if(TextUtils.isEmpty(firstName.getText().toString())){
            firstName.setError("Bitte Vornamen eintragen");
            return;
        }
        if(TextUtils.isEmpty(lastName.getText().toString())){
            lastName.setError("Bitte Nachnamen eintragen");
            return;
        }
        if(TextUtils.isEmpty(phone.getText().toString())){
            phone.setError("Bitte Telefonnummer eintragen");
            return;
        }
        if(TextUtils.isEmpty(mail.getText().toString())){
            mail.setError("Bitte Email eintragen");
            return;
        }
        if(TextUtils.isEmpty(password.getText().toString())){
            password.setError("Bitte Passwort eintragen");
            return;
        }
        if(TextUtils.isEmpty(passwordWdh.getText().toString())){
            passwordWdh.setError("Bitte Passwort eintragen");
            return;
        }
        if(password.getText().toString().equals(password.getText().toString())){
            passwordWdh.setError("Passwörter müssen übereinstimmen");
            return;
        }

        //String username, String password, String prename, String surname, String phone, String mail
        RestaurantLoginActivity.RestaurantOwnerDB.insertData(userName.getText().toString(),password.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),phone.getText().toString(),mail.getText().toString());

        Intent intent = new Intent(this, RestaurantOverviewRegistration.class);
        startActivity(intent);
        finish();
    }
}