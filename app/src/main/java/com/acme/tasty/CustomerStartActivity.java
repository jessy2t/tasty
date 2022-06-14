package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import com.acme.tasty.dataModels.DietDataModel;
import com.acme.tasty.databaseHelpers.*;

public class CustomerStartActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_start);

        //mToolbar = findViewById(R.id.main_toolbar);
        //setSupportActionBar(mToolbar);
    }

    public void navigateToHome(MenuItem item){
        Intent intent = new Intent(this, CustomerStartActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void navigateToSwipeMechanism(View view){
        Intent intent = new Intent(this, SwipeEssensort.class);
        startActivity(intent);
    }
}