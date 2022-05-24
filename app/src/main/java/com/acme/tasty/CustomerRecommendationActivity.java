package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerRecommendationActivity extends AppCompatActivity {
    private PopupWindow _popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_recommendation);
    }

    public void clickOnRestaurantRecommendation(View view) {
        Intent intent = new Intent(this, CustomerRestaurantOverviewActivity.class);
        startActivity(intent);
    }

    public void clickOnReservationOrDelivery(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_order, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        _popupWindow = new PopupWindow(popupView, width, height, focusable);

        _popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        _popupWindow.setElevation(5.0f);
    }

    public void clickOnNewRecommendation(View view) {
        //generate new recommendation
    }

    public void clickOnNewInput(View view) {
        Intent intent = new Intent(this, CustomerSwipeActivity.class);
        startActivity(intent);
    }

    public void closePopUp(View view) {
        _popupWindow.dismiss();
    }
}