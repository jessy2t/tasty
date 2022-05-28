package com.acme.tasty.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.acme.tasty.R;
import com.acme.tasty.RestaurantLoginActivity;
import org.jetbrains.annotations.NotNull;


public class CustomerRestaurantOverviewGeneralFragment extends Fragment {
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_restaurant_overview_general, container, false);
        Intent intent = getActivity().getIntent();
        String loginName = intent.getStringExtra(RestaurantLoginActivity.EXTRA_USERNAME);
        TextView restaurantName = view.findViewById(R.id.restaurant_name);
        restaurantName.setText(loginName);

        return view;
    }

}