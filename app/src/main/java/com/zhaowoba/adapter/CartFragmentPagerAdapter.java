package com.zhaowoba.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;
public class CartFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] Titles = new String[]{
            "已完成订单", "未完成订单"
    };
    List<Fragment> fragments=null;
    public CartFragmentPagerAdapter(android.support.v4.app.FragmentManager fm, List<Fragment> fragments) {
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
