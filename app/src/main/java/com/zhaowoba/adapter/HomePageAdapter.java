package com.zhaowoba.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.zhaowoba.R;
import com.zhaowoba.activity.MainActivity;
import com.zhaowoba.activity.WelcomActivity;
import com.zhaowoba.db.PreferenceUtil;

import java.util.List;

/**
 * Created by 念阿郎 on 2018/5/19.
 */

public class HomePageAdapter extends PagerAdapter {

    private List<View> mViews;

    public HomePageAdapter(List<View> views) {
        this.mViews = views;
    }

    @Override
    public int getCount() {
        // 返回实际要显示的图片数+2
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(mViews.get(arg1));
    }
    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(mViews.get(arg1), 0);
        return mViews.get(arg1);
    }
}