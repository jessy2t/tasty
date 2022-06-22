package com.acme.tasty;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.acme.tasty.databaseHelpers.RestaurantOwnerDBHelper;

import java.util.Base64;

public class RestaurantLoginActivity extends AppCompatActivity {
    public static final String RESTAURANT_USERNAME = "com.acme.tasty.RESTAURANT_USERNAME";
    public static RestaurantOwnerDBHelper RestaurantOwnerDB;
    private EditText usernameView;
    private EditText passwordView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_login);

        RestaurantOwnerDB = new RestaurantOwnerDBHelper(this);
        usernameView = findViewById(R.id.username);
        passwordView = findViewById(R.id.password);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void validateLoginCredentials(View view) {
        String usernameString = usernameView.getText().toString();
        String passwordString = passwordView.getText().toString();

        if (usernameString.isEmpty() || passwordString.isEmpty()) {
            usernameView.setError("Bitte geben Sie ein gültiges Restaurant und Passwort ein.");
            return;
        }

        Boolean checkUser = RestaurantOwnerDB.checkUsername(usernameString);
        if (!checkUser) {
            usernameView.setError("Unbekanntes Restaurant. Bitte achten Sie auf Groß- und Kleinschreibung.");
            return;
        }

        String passwordEncoded = Base64.getEncoder().encodeToString(passwordString.getBytes());
        Boolean checkCredentials = RestaurantOwnerDB.checkUsernamePassword(usernameString, passwordEncoded);
        if (!checkCredentials) {
            passwordView.setError("Passwort ungültig.");
            return;
        }

        navigateToSignedInView(usernameString);
    }

    private void navigateToSignedInView(String restaurantName) {
        Toast.makeText(RestaurantLoginActivity.this, "Willkommen "+restaurantName+"!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, RestaurantOverviewRegistration.class);
        intent.putExtra(RESTAURANT_USERNAME, restaurantName);

        startActivity(intent);
        finish();
    }

    public void navigateToRegistration(View view) {
        Intent intent = new Intent(this, RestaurantRegistration1Activity.class);
        startActivity(intent);
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}