package com.acme.tasty.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.acme.tasty.MainActivity;
import com.acme.tasty.R;
import com.acme.tasty.RestaurantLoginActivity;
import com.acme.tasty.dataModels.AddressDataModel;
import com.acme.tasty.dataModels.CityDataModel;
import com.acme.tasty.dataModels.OpeningHoursDataModel;
import com.acme.tasty.dataModels.RestaurantDataModel;
import com.acme.tasty.databaseHelpers.AddressDBHelper;
import com.acme.tasty.databaseHelpers.CityDBHelper;
import com.acme.tasty.databaseHelpers.OpeningHoursDBHelper;
import com.acme.tasty.databaseHelpers.RestaurantDBHelper;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class CustomerRestaurantOverviewGeneralFragment extends Fragment {
    RestaurantDBHelper RestaurantDB;
    OpeningHoursDBHelper OpeningHoursDB;
    TextView restaurantName;
    TextView restaurantStreet;
    TextView restaurantCity;
    ChipGroup chipGroup;
    TextView weekdayOpeningHoursView;
    TextView saturdayOpeningHoursView;
    TextView sundayOpeningHoursView;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_restaurant_overview_general, container, false);
        Intent intent = getActivity().getIntent();

        restaurantName = view.findViewById(R.id.restaurant_name);
        restaurantStreet = view.findViewById(R.id.restaurant_address_streetNumber);
        restaurantCity = view.findViewById(R.id.restaurant_address_zipCodeCity);
        chipGroup = view.findViewById(R.id.chip_group);
        weekdayOpeningHoursView = view.findViewById(R.id.mondayToFriday_opening_hours);
        saturdayOpeningHoursView = view.findViewById(R.id.saturday_opening_hours);
        sundayOpeningHoursView = view.findViewById(R.id.sunday_opening_hours);

        setRestaurantData(view);

        if(intent.getBooleanExtra(RestaurantLoginActivity.EXTRA_IS_RESTAURANT_VIEW, false))
            disableButtons(view);

        return view;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        RestaurantDB = new RestaurantDBHelper(getActivity());
        OpeningHoursDB = new OpeningHoursDBHelper(getActivity());
    }

    private void setRestaurantData(View view) {
        //String loginName = intent.getStringExtra(RestaurantLoginActivity.EXTRA_USERNAME);
        RestaurantDataModel restaurant = MainActivity.RestaurantDB.getRestaurant("Tony's Tacos");

        restaurantName.setText(restaurant.toString());
        restaurantStreet.setText(restaurant.Address.toString());
        restaurantCity.setText(restaurant.Address.City.toString());

        ArrayList<String> restaurantAttributes = restaurant.Attributes.getAttributes();
        for (String attribute : restaurantAttributes) {
            Chip chip = new Chip(getActivity());
            chip.setText(attribute);
            chipGroup.addView(chip);
        }

        OpeningHoursDataModel weekdayOpeningHours = OpeningHoursDB.getOpeningHours(1);
        weekdayOpeningHoursView.setText(weekdayOpeningHours.toString());

        OpeningHoursDataModel saturdayOpeningHours = OpeningHoursDB.getOpeningHours(2);
        saturdayOpeningHoursView.setText(saturdayOpeningHours.toString());

        OpeningHoursDataModel sundayOpeningHours = OpeningHoursDB.getOpeningHours(3);
        sundayOpeningHoursView.setText(sundayOpeningHours.toString());
    }

    private void disableButtons(View view) {
        FloatingActionButton deliveryButton = view.findViewById(R.id.delivery_btn);
        deliveryButton.setEnabled(false);
        FloatingActionButton reservationButton = view.findViewById(R.id.reservation_btn);
        reservationButton.setEnabled(false);
    }
}