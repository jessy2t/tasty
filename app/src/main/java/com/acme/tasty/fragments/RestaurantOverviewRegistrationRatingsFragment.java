package com.acme.tasty.fragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.acme.tasty.R;
import com.acme.tasty.dataModels.RatingDataModel;
import com.acme.tasty.databaseHelpers.RatingDBHelper;
import com.google.android.material.chip.Chip;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RestaurantOverviewRegistrationRatingsFragment extends Fragment {
    RatingDBHelper RatingDB;
    Chip newestChip;
    Chip highestChip;
    Chip lowestChip;
    TextView averageRating;
    RatingBar averageRatingBar;
    TextView ratingTitle1;
    RatingBar ratingBar1;
    TextView ratingDate1;
    TextView ratingTitle2;
    RatingBar ratingBar2;
    TextView ratingDate2;
    TextView ratingTitle3;
    RatingBar ratingBar3;
    TextView ratingDate3;
    TextView ratingTitle4;
    RatingBar ratingBar4;
    TextView ratingDate4;
    TextView ratingTitle5;
    RatingBar ratingBar5;
    TextView ratingDate5;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        if(RatingDB.getRatingOrderedByNewest("Tony's Tacos").equals(new ArrayList<RatingDataModel>())) {
            return inflater.inflate(R.layout.fragment_restaurant_overview_no_ratings, container, false);
        }
        else {
            View view = inflater.inflate(R.layout.fragment_customer_restaurant_overview_ratings, container, false);

            newestChip = view.findViewById(R.id.sort_by_newest);
            highestChip = view.findViewById(R.id.sort_by_highest);
            lowestChip = view.findViewById(R.id.sort_by_lowest);

            averageRating = view.findViewById(R.id.average_rating);
            averageRatingBar = view.findViewById(R.id.average_ratingBar);
            ratingTitle1 = view.findViewById(R.id.rating_title_1);
            ratingBar1 = view.findViewById(R.id.ratingBar_1);
            ratingDate1 = view.findViewById(R.id.date_1);
            ratingTitle2 = view.findViewById(R.id.rating_title_2);
            ratingBar2 = view.findViewById(R.id.ratingBar_2);
            ratingDate2 = view.findViewById(R.id.date_2);
            ratingTitle3 = view.findViewById(R.id.rating_title_3);
            ratingBar3 = view.findViewById(R.id.ratingBar_3);
            ratingDate3 = view.findViewById(R.id.date_3);
            ratingTitle4 = view.findViewById(R.id.rating_title_4);
            ratingBar4 = view.findViewById(R.id.ratingBar_4);
            ratingDate4 = view.findViewById(R.id.date_4);
            ratingTitle5 = view.findViewById(R.id.rating_title_5);
            ratingBar5 = view.findViewById(R.id.ratingBar_5);
            ratingDate5 = view.findViewById(R.id.date_5);
            sortByNewest();

            return view;
        }
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        RatingDB = new RatingDBHelper(getActivity());
    }

    public void sortByNewest() {
        focusChip(newestChip);
        defocusChip(highestChip);
        defocusChip(lowestChip);

        ArrayList<RatingDataModel> ratings = RatingDB.getRatingOrderedByNewest("Tony's Tacos");
        setRatingsData(ratings);
    }

    public void sortByHighest() {
        defocusChip(newestChip);
        focusChip(highestChip);
        defocusChip(lowestChip);

        ArrayList<RatingDataModel> ratings = RatingDB.getRatingOrderedByHighest("Tony's Tacos");
        setRatingsData(ratings);
    }

    public void sortByLowest() {
        defocusChip(newestChip);
        defocusChip(highestChip);
        focusChip(lowestChip);

        ArrayList<RatingDataModel> ratings = RatingDB.getRatingOrderedByLowest("Tony's Tacos");
        setRatingsData(ratings);
    }

    private void focusChip(Chip chip) {
        chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#94cc88")));
    }

    private void defocusChip(Chip chip) {
        chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#e5e5e5")));
    }

    private void setRatingsData(ArrayList<RatingDataModel> ratings) {
        Float averageRatingValue = Float.valueOf(0);
        for (RatingDataModel rating : ratings) {
            averageRatingValue += rating.Rating;
        }
        averageRatingValue /= ratings.size();
        averageRating.setText(String.valueOf(averageRatingValue));
        averageRatingBar.setRating(averageRatingValue);

        ratingTitle1.setText(ratings.get(0).ReviewTitle);
        ratingBar1.setRating(ratings.get(0).Rating);
        ratingDate1.setText(ratings.get(0).ReviewDate);

        ratingTitle2.setText(ratings.get(1).ReviewTitle);
        ratingBar2.setRating(ratings.get(1).Rating);
        ratingDate2.setText(ratings.get(1).ReviewDate);

        ratingTitle3.setText(ratings.get(2).ReviewTitle);
        ratingBar3.setRating(ratings.get(2).Rating);
        ratingDate3.setText(ratings.get(2).ReviewDate);

        ratingTitle4.setText(ratings.get(3).ReviewTitle);
        ratingBar4.setRating(ratings.get(3).Rating);
        ratingDate4.setText(ratings.get(3).ReviewDate);

        ratingTitle5.setText(ratings.get(4).ReviewTitle);
        ratingBar5.setRating(ratings.get(4).Rating);
        ratingDate5.setText(ratings.get(4).ReviewDate);
    }
}