package com.example.acer.vale_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {

       return new FirstFragment();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Home";
            case 1:
                return "Trips";
            case 2:
                return "My Account";
        }
        return "Default Text";
    }


    @Override
    public int getCount() {
        return 3;

    }
}
