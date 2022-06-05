package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.acme.tasty.dataModels.DietDataModel;
import com.acme.tasty.databaseHelpers.*;

public class CustomerStartActivity extends AppCompatActivity {
    public static DietDBHelper DietDB;
    public static PriceRangeDBHelper PriceRangeDB;
    public static SuggestionBasisDBHelper SuggestionBasisDB;
    public static SuggestionDBHelper SuggestionDB;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_start);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        createDatabasesConnection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void navigateToSwipeMechanism(View view){
        Intent intent = new Intent(this, CustomerSwipeActivity.class);
        startActivity(intent);
    }

    private void createDatabasesConnection() {
        DietDB = new DietDBHelper(this);
        PriceRangeDB = new PriceRangeDBHelper(this);
        SuggestionBasisDB = new SuggestionBasisDBHelper(this);
        SuggestionDB = new SuggestionDBHelper(this);
    }
}