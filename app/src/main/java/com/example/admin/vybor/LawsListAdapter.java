package com.example.admin.vybor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


public class LawsListAdapter extends FragmentStatePagerAdapter implements UpdateableFragment {
    public LawsListAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
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
//        return super.getItemPosition(object);
        return POSITION_NONE;
    }

}
