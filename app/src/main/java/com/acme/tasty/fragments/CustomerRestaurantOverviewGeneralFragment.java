package com.acme.tasty.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import com.acme.tasty.CustomerRecommendationActivity;
import com.acme.tasty.MainActivity;
import com.acme.tasty.R;
import com.acme.tasty.dataModels.OpeningHoursDataModel;
import com.acme.tasty.dataModels.RestaurantDataModel;
import com.acme.tasty.databaseHelpers.OpeningHoursDBHelper;
import com.acme.tasty.databaseHelpers.RestaurantDBHelper;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;


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
    ImageView restaurantImage;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_restaurant_overview_general, container, false);
        getUIElements(view);
        setRestaurantData();
        return view;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        RestaurantDB = new RestaurantDBHelper(getActivity());
        OpeningHoursDB = new OpeningHoursDBHelper(getActivity());
    }

    private void getUIElements(View view) {
        restaurantName = view.findViewById(R.id.restaurant_name);
        restaurantStreet = view.findViewById(R.id.restaurant_address_streetNumber);
        restaurantCity = view.findViewById(R.id.restaurant_address_zipCodeCity);
        chipGroup = view.findViewById(R.id.chip_group);
        weekdayOpeningHoursView = view.findViewById(R.id.mondayToFriday_opening_hours);
        saturdayOpeningHoursView = view.findViewById(R.id.saturday_opening_hours);
        sundayOpeningHoursView = view.findViewById(R.id.sunday_opening_hours);
        restaurantImage = view.findViewById(R.id.restaurant_logo);
    }
    private void setRestaurantData() {
        String restaurantSuggestion = Objects.requireNonNull(getActivity()).getIntent().getStringExtra(CustomerRecommendationActivity
                .RESTAURANT_SUGGESTION);
        RestaurantDataModel restaurant = MainActivity.RestaurantDB.getRestaurant(restaurantSuggestion);

        setRestaurantImage(restaurant.ImageName);
        restaurantName.setText(restaurant.toString());
        restaurantStreet.setText(restaurant.Address.toString());
        restaurantCity.setText(restaurant.Address.City.toString());

        ArrayList<String> restaurantAttributes = restaurant.Attributes.getAttributes();
        for (String attribute : restaurantAttributes) {
            Chip chip = new Chip(getActivity());
            chip.setText(attribute);
            chip.setTextSize(12);
            chip.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            chipGroup.addView(chip);
        }

        OpeningHoursDataModel weekdayOpeningHours = OpeningHoursDB.getOpeningHours(1);
        weekdayOpeningHoursView.setText(weekdayOpeningHours.toString());

        OpeningHoursDataModel saturdayOpeningHours = OpeningHoursDB.getOpeningHours(2);
        saturdayOpeningHoursView.setText(saturdayOpeningHours.toString());

        OpeningHoursDataModel sundayOpeningHours = OpeningHoursDB.getOpeningHours(3);
        sundayOpeningHoursView.setText(sundayOpeningHours.toString());
    }

    private void setRestaurantImage(String restaurantImageName) {
        switch (restaurantImageName) {
            case "taco":
                restaurantImage.setImageDrawable(getResources().getDrawable(R.drawable.taco));
                break;
            case "burger":
                restaurantImage.setImageDrawable(getResources().getDrawable(R.drawable.burger));
                break;
            case "sausage":
                restaurantImage.setImageDrawable(getResources().getDrawable(R.drawable.sausage));
                break;
            case "curry":
                restaurantImage.setImageDrawable(getResources().getDrawable(R.drawable.curry));
                break;
            case "rice":
                restaurantImage.setImageDrawable(getResources().getDrawable(R.drawable.rice));
                break;
            case "indonesian":
                restaurantImage.setImageDrawable(getResources().getDrawable(R.drawable.indonesian));
                break;
            case "pizza":
                restaurantImage.setImageDrawable(getResources().getDrawable(R.drawable.pizza));
                break;
            default:
                restaurantImage.setImageDrawable(getResources().getDrawable(R.drawable.unknown_restaurant));
                break;
        }
    }
}