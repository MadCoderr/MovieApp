package com.example.farooqi.movieapp.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.farooqi.movieapp.fragments.CelebrityFragment;
import com.example.farooqi.movieapp.fragments.HomeFragment;
import com.example.farooqi.movieapp.fragments.MovieFragment;
import com.example.farooqi.movieapp.fragments.NotificationFragment;
import com.example.farooqi.movieapp.fragments.TVFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();

            case 1:
                return new MovieFragment();

            case 2:
                return new TVFragment();

            case 3:
                return new CelebrityFragment();

            case 4:
                return new NotificationFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";

            case 1:
                return "Movie";

            case 2:
                return "TV";

            case 3:
                return "Celebrities";

            case 4:
                return "Notification";

            default:
                return "Home";
        }
    }
}
