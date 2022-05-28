package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private List<LoginUser> loginRepository = new ArrayList<>();
    public static final String EXTRA_USERNAME = "com.acme.tasty.RestaurantLoginActivity.Username";
    private EditText username;
    private EditText password;
    private Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_login);

        username = findViewById(R.id.username);        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        loginRepository.add(new LoginUser("Tony's Tacos", "tasty"));
        loginRepository.add(new LoginUser("Pippa's Pizzeria", "tasty"));
    }

    public void login(View view) {
        if (userFoundInLoginRepository()) {
            Intent intent = new Intent(this, CustomerRestaurantOverviewActivity.class);
            intent.putExtra(EXTRA_USERNAME, username.getText().toString());
            startActivity(intent);
        }
        else
            Toast.makeText(RestaurantLoginActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();
    }

    private boolean userFoundInLoginRepository() {
        LoginUser enteredCredentials = new LoginUser(username.getText().toString(), password.getText().toString());
        for (int i = 0; i < loginRepository.size(); ++i) {
            if(enteredCredentials.Name.equals(loginRepository.get(i).Name) &&
                    enteredCredentials.Password.equals(loginRepository.get(i).Password))
                return true;
        }
        return false;
    }

    public void navigateToRegistration(View view) {
        Intent intent = new Intent(this, RestaurantRegistrationActivity.class);
        startActivity(intent);
    }
}