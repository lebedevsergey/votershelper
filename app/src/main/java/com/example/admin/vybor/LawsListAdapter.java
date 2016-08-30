package com.example.admin.vybor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


public class LawsListAdapter extends FragmentStatePagerAdapter implements UpdateableFragment {
    final int PAGE_NUM = 3;

    public LawsListAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }

    @Override
    public Fragment getItem(int position) {
        return LawsListFragment.newInstance(position);
    }

    public void update() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof UpdateableFragment) {
            ((UpdateableFragment) object).update();
        }
        return POSITION_NONE;
    }

}
