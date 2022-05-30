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

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class RestaurantLoginActivity extends AppCompatActivity {

    public static class LoginUser {
        public String Name;
        public String Password;
        public LoginUser(String name, String password) {
            this.Name = name;
            this.Password = password;
        }
    }

    abstract static class LoginResult {}
    public static class UnknownRestaurantResult extends LoginResult {}
    public static class WrongPasswordResult extends LoginResult {}
    public static class AccountFoundResult extends LoginResult {}
    private final List<LoginUser> loginRepository = new ArrayList<>();
    public static final String EXTRA_USERNAME = "com.acme.tasty.RestaurantLoginActivity.Username";
    public static final String EXTRA_IS_RESTAURANT_VIEW = "com.acme.tasty.RestaurantLoginActivity.IsRestaurantView";
    private EditText username;
    private EditText password;
    private TextView validationMessage;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        validationMessage = findViewById(R.id.validation_message);
        String encodedPassword = Base64.getEncoder().encodeToString(("tasty").getBytes());
        loginRepository.add(new LoginUser("Tony's Tacos", encodedPassword));
        loginRepository.add(new LoginUser("Pippa's Pizzeria", encodedPassword));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void validateLoginCredentials(View view) {
        if (username.getText().toString().isEmpty()) {
            showFailedLoginValidationMessage("Bitte geben Sie den Restaurant-Namen ein.");
            return;
        }

        if (password.getText().toString().isEmpty()) {
            showFailedLoginValidationMessage("Bitte geben Sie ein gültiges Passwort ein.");
            return;
        }

        Class<? extends LoginResult> loginResult = compareLoginCredentialsToLoginRepository().getClass();
        if (loginResult.equals(UnknownRestaurantResult.class)) {
            showFailedLoginValidationMessage("Unbekanntes Restaurant. Bitte achten Sie auf Groß- und Kleinschreibung.");
        }
        else if (loginResult.equals(WrongPasswordResult.class)) {
            showFailedLoginValidationMessage("Passwort ungültig.");
        }
        else {
            validationMessage.setVisibility(View.INVISIBLE);
            Toast.makeText(RestaurantLoginActivity.this, "Willkommen "+username.getText().toString()+"!", Toast.LENGTH_LONG).show();
            navigateToSignedInView();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LoginResult compareLoginCredentialsToLoginRepository() {
        String passwordEncoded = Base64.getEncoder().encodeToString((password.getText().toString()).getBytes());
        LoginUser enteredCredentials = new LoginUser(username.getText().toString(), passwordEncoded);
        for (LoginUser loginUser : loginRepository) {

            if (enteredCredentials.Name.equals(loginUser.Name)) {
                if (enteredCredentials.Password.equals(loginUser.Password)) {
                    return new AccountFoundResult();
                }
                return new WrongPasswordResult();
            }
        }
        return new UnknownRestaurantResult();
    }

    private void showFailedLoginValidationMessage(String message) {
        validationMessage.setVisibility(View.VISIBLE);
        validationMessage.setText(message);
        Toast.makeText(RestaurantLoginActivity.this, "Anmeldung fehlgeschlagen", Toast.LENGTH_LONG).show();
    }

    private void navigateToSignedInView() {
        Intent intent = new Intent(this, CustomerRestaurantOverviewActivity.class);
        intent.putExtra(EXTRA_USERNAME, username.getText().toString());
        intent.putExtra(EXTRA_IS_RESTAURANT_VIEW, true);

        startActivity(intent);
        finish();
    }

    public void navigateToRegistration(View view) {
        Intent intent = new Intent(this, RestaurantRegistrationActivity.class);
        startActivity(intent);
    }
}