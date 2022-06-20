package com.acme.tasty;

import android.content.Context;
import android.os.Build;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.acme.tasty.databaseHelpers.RestaurantOwnerDBHelper;

import java.util.Base64;

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
        userName = findViewById(R.id.username_register);
        userName.setText(getIntent().getStringExtra(RestaurantLoginActivity.RESTAURANT_USERNAME));
        userName.setEnabled(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void navigateToRestaurantOverviewRegistration(View view){

        firstName = findViewById(R.id.inhaberVorname);
        lastName = findViewById(R.id.inhaberNachname);
        phone = findViewById(R.id.inhaberTelefonnummer);
        mail = findViewById(R.id.inhaberEmail);
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
        if(TextUtils.isEmpty(mail.getText().toString())) {
            mail.setError("Bitte Email eintragen");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches()){
            mail.setError("Bitte gültige Emailadresse eintragen");
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
        if(!(password.getText().toString().equals(passwordWdh.getText().toString()))){
            passwordWdh.setError("Passwörter müssen übereinstimmen");
            return;
        }

        //String username, String password, String prename, String surname, String phone, String mail
        String passwordEncoded = Base64.getEncoder().encodeToString(password.getText().toString().getBytes());
        if(RestaurantLoginActivity.RestaurantOwnerDB.insertData(userName.getText().toString(),
                passwordEncoded,firstName.getText().toString(),lastName.getText().toString(),
                phone.getText().toString(),mail.getText().toString())){
            Toast.makeText(this, "Registrierung erfolgreich", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, RestaurantOverviewRegistration.class);
            intent.putExtra(RestaurantLoginActivity.RESTAURANT_USERNAME, userName.getText().toString());
            startActivity(intent);
            finish();
        }
        else
            Toast.makeText(this, "Konto konnte nicht angelegt werden. Prüfe die Eingaben.", Toast.LENGTH_LONG).show();
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}