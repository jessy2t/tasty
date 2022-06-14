package com.acme.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.acme.tasty.dataModels.SuggestionBasisDataModel;
import com.acme.tasty.popup.PopupWindowService;

import static com.acme.tasty.RestaurantLoginActivity.EXTRA_USERNAME;

public class CustomerRecommendationActivity extends AppCompatActivity {
    public static final String RESTAURANT_SUGGESTION = "com.acme.tasty.RESTAURANT_SUGGESTION";
    private PopupWindow _popupWindow;
    private Toolbar mToolbar;
    private String restaurantName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_recommendation);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        restaurantName = getRestaurantFromSuggestionBasis();
        ((TextView)findViewById(R.id.restaurant_suggestion)).setText(restaurantName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private String getRestaurantFromSuggestionBasis() {
        SuggestionBasisDataModel suggestionBasis = SwipePreisrahmen.SuggestionBasisDB.getLastInsertedSuggestionBasis();
        return MainActivity.RestaurantDB.getRestaurantBySuggestionBasis(suggestionBasis).Name;
    }

    public void clickOnRestaurantRecommendation(View view) {
        Intent intent = new Intent(this, CustomerRestaurantOverviewActivity.class);
        intent.putExtra(RESTAURANT_SUGGESTION, restaurantName);
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
        Intent intent = new Intent(this, SwipeEssensort.class);
        startActivity(intent);
    }

    public void navigateToPreferences(MenuItem item){
        Intent intent = new Intent(this, CustomerPreferencesActivity.class);
        startActivity(intent);
    }

    public void closePopUp(View view) {
        PopupWindowService.closePopUp(_popupWindow, view);
    }

    public void navigateToHome(MenuItem item){
        Intent intent = new Intent(this, CustomerStartActivity.class);
        startActivity(intent);
    }
}