//package com.example.admin.vybor;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//
//
///**
// * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
// * one of the sections/tabs/pages.
// */
//public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//    public SectionsPagerAdapter(FragmentManager fm) {
//        super(fm);
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        // getItem is called to instantiate the fragment for the given page.
//        // Return a PlaceholderFragment (defined as a static inner class below).
////        return PlaceholderFragment.newInstance(position + 1);
//        return ArrayListFragment.newInstance(4);
//    }
//
//
//    @Override
//    public int getCount() {
//        // Show 3 total pages.
//        return 3;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 0:
//                return "ПАРТИИ";
//            case 1:
//                return "ДЕПУТАТЫ";
//            case 2:
//                return "SECTION 3";
//        }
//        return null;
//    }
//}