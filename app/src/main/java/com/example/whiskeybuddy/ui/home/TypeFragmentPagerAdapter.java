package com.example.whiskeybuddy.ui.home;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TypeFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] TYPES = {"Scotch", "Bourbon", "Canadian", "Irish"};

    private Context mContext;

    public TypeFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        return new TypeFragment(TYPES[position]);
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return TYPES[position];
    }

}