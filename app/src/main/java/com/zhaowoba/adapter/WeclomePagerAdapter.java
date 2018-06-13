package com.zhaowoba.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhaowoba.R;
import com.zhaowoba.activity.LoginActivity;
import com.zhaowoba.activity.WelcomActivity;
import com.zhaowoba.db.PreferenceUtil;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by 念阿郎 on 2018/5/16.
 */

public class WeclomePagerAdapter extends PagerAdapter {

    private List<View> mViews;
    private WelcomActivity mContext;

    public WeclomePagerAdapter(Activity context, List<View> views) {
        this.mViews = views;
        this.mContext = (WelcomActivity) context;
    }

    @Override
    public int getCount() {
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

    // 实例化页卡，如果变为最后一页，则获取它的button并且添加点击事件
    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(mViews.get(arg1), 0);
        if (arg1 == mViews.size() - 1) {
            Button enterBtn = (Button) arg0
                    .findViewById(R.id.bt_login);
            enterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 将isShow保存为true，并进入登录界面
                    PreferenceUtil.setBoolean(mContext,
                            PreferenceUtil.SHOW_GUIDE, true);
                    //并调到登陆界面
                    mContext.jumpLogin();
                }
            });
        }
        return mViews.get(arg1);
    }
}
