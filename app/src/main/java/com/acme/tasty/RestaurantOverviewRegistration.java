package com.acme.tasty;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.acme.tasty.fragments.RestaurantOverviewRegistrationGeneralFragment;
import com.acme.tasty.fragments.RestaurantOverviewRegistrationRatingsFragment;
import com.acme.tasty.popup.PopupWindowService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RestaurantOverviewRegistration extends AppCompatActivity {

    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
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

    private PopupWindow _popupWindow;
    private RestaurantOverviewRegistrationRatingsFragment ratingsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_restaurant_overview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setFragments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu_restaurant, menu);
        return true;
    }

    private void setFragments() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        RestaurantOverviewRegistrationGeneralFragment generalFragment = new RestaurantOverviewRegistrationGeneralFragment();
        ratingsFragment = new RestaurantOverviewRegistrationRatingsFragment();

        viewPagerAdapter.addFragment(generalFragment,"");
        viewPagerAdapter.addFragment(ratingsFragment,"");
        viewPager.setAdapter(viewPagerAdapter);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setText("Übersicht");
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText("Bewertungen");
    }

    public void clickOnReservationOrDelivery(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        _popupWindow = PopupWindowService.getOrderConfirmationPopupWindow(inflater, view);
    }

    public void navigateToLogIn(MenuItem item){
        Intent intent = new Intent(this, RestaurantLoginActivity.class);
        startActivity(intent);
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
}