package com.acme.tasty;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.acme.tasty.databaseHelpers.RestaurantOwnerDBHelper;

import java.util.Base64;

public class RestaurantLoginActivity extends AppCompatActivity {
    public static final String RESTAURANT_USERNAME = "com.acme.tasty.RESTAURANT_USERNAME";
    private EditText usernameView;
    private EditText passwordView;
    private TextView validationMessageView;
    public static RestaurantOwnerDBHelper RestaurantOwnerDB;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_login);
        usernameView = findViewById(R.id.username);
        passwordView = findViewById(R.id.password);
        validationMessageView = findViewById(R.id.validation_message);

        RestaurantOwnerDB = new RestaurantOwnerDBHelper(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void validateLoginCredentials(View view) {
        String usernameString = usernameView.getText().toString();
        String passwordString = passwordView.getText().toString();

        if (usernameString.isEmpty() || passwordString.isEmpty()) {
            showFailedLoginValidationMessage("Bitte geben Sie ein gültiges Restaurant und Passwort ein.");
            return;
        }

        Boolean checkUser = RestaurantOwnerDB.checkUsername(usernameString);
        if (!checkUser)
            showFailedLoginValidationMessage("Unbekanntes Restaurant. Bitte achten Sie auf Groß- und Kleinschreibung.");

        else {
            String passwordEncoded = Base64.getEncoder().encodeToString(passwordString.getBytes());
            if (!RestaurantOwnerDB.checkUsernamePassword(usernameString, passwordEncoded)) {
                showFailedLoginValidationMessage("Passwort ungültig.");
            }
            else {
                validationMessageView.setVisibility(View.INVISIBLE);
                Toast.makeText(RestaurantLoginActivity.this, "Willkommen "+usernameString+"!", Toast.LENGTH_LONG).show();
                navigateToSignedInView(usernameString);
            }
        }
    }

    private void showFailedLoginValidationMessage(String message) {
        validationMessageView.setVisibility(View.VISIBLE);
        validationMessageView.setText(message);
        Toast.makeText(RestaurantLoginActivity.this, "Anmeldung fehlgeschlagen", Toast.LENGTH_LONG).show();
    }

    private void navigateToSignedInView(String restaurantName) {
        Intent intent = new Intent(this, RestaurantOverviewRegistration.class);
        intent.putExtra(RESTAURANT_USERNAME, restaurantName);

        startActivity(intent);
        finish();
    }

    public void navigateToRegistration(View view) {
        Intent intent = new Intent(this, RestaurantRegistration1Activity.class);
        startActivity(intent);
    }
}