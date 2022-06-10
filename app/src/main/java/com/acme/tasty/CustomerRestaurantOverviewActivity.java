package com.acme.tasty;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.acme.tasty.dataModels.RatingDataModel;
import com.acme.tasty.databaseHelpers.RatingDBHelper;
import com.acme.tasty.fragments.CustomerRestaurantOverviewGeneralFragment;
import com.acme.tasty.fragments.CustomerRestaurantOverviewRatingsFragment;
import com.acme.tasty.popup.PopupWindowService;
import com.google.android.material.chip.Chip;
import com.google.android.material.tabs.TabLayout;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomerRestaurantOverviewActivity extends AppCompatActivity {
    private PopupWindow _popupWindow;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private CustomerRestaurantOverviewGeneralFragment generalFragment;
    private CustomerRestaurantOverviewRatingsFragment ratingsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_restaurant_overview);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        generalFragment = new CustomerRestaurantOverviewGeneralFragment();
        ratingsFragment = new CustomerRestaurantOverviewRatingsFragment();
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(generalFragment,"");
        viewPagerAdapter.addFragment(ratingsFragment,"");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setText("Übersicht");
        tabLayout.getTabAt(1).setText("Bewertungen");
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitles = new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }

    public void clickOnReservationOrDelivery(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        _popupWindow = PopupWindowService.getOrderConfirmationPopupWindow(inflater, view);
    }

    public void closePopUp(View view) {
        PopupWindowService.closePopUp(_popupWindow, view);
    }

    public void sortByNewestDate(View view) {
        ratingsFragment.sortByNewest();
    }

    public void sortByHighestRating(View view) {
        ratingsFragment.sortByHighest();
    }

    public void sortByLowestRating(View view) {
        ratingsFragment.sortByLowest();
    }

    public void navigateToHome(MenuItem item){
        Intent intent = new Intent(this, CustomerStartActivity.class);
        startActivity(intent);
    }
}