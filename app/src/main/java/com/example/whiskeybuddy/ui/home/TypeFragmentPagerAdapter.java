package com.example.whiskeybuddy.ui.home;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whiskeybuddy.R;

public class TypeFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] TYPES = {};
    private int number_of_types;

    private Context mContext;

    public TypeFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        TYPES = mContext.getResources().getStringArray(R.array.whiskey_types);
        number_of_types = TYPES.length;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        return new TypeFragment(TYPES[position]);
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return number_of_types;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return TYPES[position];
    }

}