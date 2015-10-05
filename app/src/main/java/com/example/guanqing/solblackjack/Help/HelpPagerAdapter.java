package com.example.guanqing.solblackjack.Help;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Guanqing on 2015/10/5.
 */
public class HelpPagerAdapter extends FragmentStatePagerAdapter {
    static final int ITEMS = 4;
    public HelpPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
    }
    @Override
    public int getCount() {
        return ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        return HelpTextFragment.newInstance(position);
    }
}
