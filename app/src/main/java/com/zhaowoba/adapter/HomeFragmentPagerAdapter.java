package com.zhaowoba.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;
/*
 * fragment的适配器
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] Titles = new String[]{
            "取快递", "取水壶", "带饭", "旧物置换"
    };
    List<android.support.v4.app.Fragment> fragments=null;
    public HomeFragmentPagerAdapter(android.support.v4.app.FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }
    @Override
    public int getCount() {
        return Titles.length;
    }
}
