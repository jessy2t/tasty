package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.acme.tasty.popup.PopupWindowService;

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
        _popupWindow = PopupWindowService.getOrderConfirmationPopupWindow(inflater, view);
    }

    public void clickOnNewRecommendation(View view) {
        //generate new recommendation
    }

    public void clickOnNewInput(View view) {
        Intent intent = new Intent(this, CustomerSwipeActivity.class);
        startActivity(intent);
    }

    public void closePopUp(View view) {
        PopupWindowService.closePopUp(_popupWindow, view);
    }
}