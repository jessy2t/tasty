package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class SwipePreisrahmen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_preisrahmen);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekbar);
        final TextView seekBarValue = (TextView)findViewById(R.id.seekbarvalue);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void navigateToCustomerRecommendation(View view) {
        Intent intent = new Intent(this, CustomerRecommendationActivity.class);
        startActivity(intent);
    }
}