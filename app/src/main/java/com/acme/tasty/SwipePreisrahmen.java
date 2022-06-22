package com.acme.tasty;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.acme.tasty.databaseHelpers.DietDBHelper;
import com.acme.tasty.databaseHelpers.PriceRangeDBHelper;
import com.acme.tasty.databaseHelpers.SuggestionBasisDBHelper;

public class SwipePreisrahmen extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static SuggestionBasisDBHelper SuggestionBasisDB;
    @SuppressLint("StaticFieldLeak")
    public static DietDBHelper DietDB;
    @SuppressLint("StaticFieldLeak")
    public static PriceRangeDBHelper PriceRangeDB;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_preisrahmen);

        seekBar = findViewById(R.id.seekbar);
        final TextView seekBarValue = findViewById(R.id.seekbarvalue);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(progress + " €");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void navigateToCustomerRecommendation(View view) {
        SuggestionBasisDB = new SuggestionBasisDBHelper(this);
        DietDB = new DietDBHelper(this);
        PriceRangeDB = new PriceRangeDBHelper(this);
        String deliveryOrReservation = getIntent().getStringExtra(SwipeEssensort.DELIVERY_OR_RESERVATION);
        String foodOrRestaurantSuggestion = getIntent().getStringExtra(SwipeEmpfehlungsart.FOOD_OR_RESTAURANT_SUGGESTION);
        String dietPreference = getIntent().getStringExtra(SwipeBeschraenkungen.DIET_PREFERENCE);

        SuggestionBasisDB.insertData(deliveryOrReservation, foodOrRestaurantSuggestion, dietPreference, 0,
                seekBar.getProgress());

        Intent nextIntent = new Intent(this, CustomerRecommendationActivity.class);
        startActivity(nextIntent);
    }
}