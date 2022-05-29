package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RestaurantLoginActivity extends AppCompatActivity {

    public class LoginUser {
        public String Name;
        public String Password;
        public LoginUser(String name, String password) {
            this.Name = name;
            this.Password = password;
        }
    }

    abstract class LoginResult {}
    public class UnknownRestaurantResult extends LoginResult {}
    public class WrongPasswordResult extends LoginResult {}
    public class AccountFoundResult extends LoginResult {}
    private List<LoginUser> loginRepository = new ArrayList<>();
    public static final String EXTRA_USERNAME = "com.acme.tasty.RestaurantLoginActivity.Username";
    public static final String EXTRA_IS_RESTAURANT_VIEW = "com.acme.tasty.RestaurantLoginActivity.IsRestaurantView";
    private EditText username;
    private EditText password;
    private TextView validationMessage;
    private Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        validationMessage = findViewById(R.id.validation_message);
        login = findViewById(R.id.login);
        loginRepository.add(new LoginUser("Tony's Tacos", "tasty"));
        loginRepository.add(new LoginUser("Pippa's Pizzeria", "tasty"));
    }

    public void login(View view) {
        if (username.getText().toString().isEmpty() || username.getText().toString() == null) {
            showFailedLoginValidationMessage("Bitte geben Sie den Restaurant-Namen ein.");
            return;
        }
        if (password.getText().toString().isEmpty() || password.getText().toString() == null) {
            showFailedLoginValidationMessage("Bitte geben Sie ein gültiges Passwort ein.");
            return;
        }

        Class<? extends LoginResult> loginResult = userFoundInLoginRepository().getClass();
        if (loginResult.equals(AccountFoundResult.class)) {
            Intent intent = new Intent(this, CustomerRestaurantOverviewActivity.class);
            intent.putExtra(EXTRA_USERNAME, username.getText().toString());
            intent.putExtra(EXTRA_IS_RESTAURANT_VIEW, true);
            startActivity(intent);
            validationMessage.setVisibility(View.INVISIBLE);
            Toast.makeText(RestaurantLoginActivity.this, "Willkommen "+username.getText().toString()+"!", Toast.LENGTH_LONG).show();
        }

        else if (loginResult.equals(WrongPasswordResult.class)) {
            showFailedLoginValidationMessage("Passwort ungültig.");
        }

        else {
            showFailedLoginValidationMessage("Unbekanntes Restaurant. Bitte achten Sie auf Groß- und Kleinschreibung.");
        }
    }

    private LoginResult userFoundInLoginRepository() {
        LoginUser enteredCredentials = new LoginUser(username.getText().toString(), password.getText().toString());
        for (int i = 0; i < loginRepository.size(); ++i) {

            if (enteredCredentials.Name.equals(loginRepository.get(i).Name)) {
                if (enteredCredentials.Password.equals(loginRepository.get(i).Password)) {
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

    public void navigateToRegistration(View view) {
        Intent intent = new Intent(this, RestaurantRegistrationActivity.class);
        startActivity(intent);
    }
}