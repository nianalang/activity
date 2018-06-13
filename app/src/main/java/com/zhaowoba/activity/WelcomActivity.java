package com.zhaowoba.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.zhaowoba.R;
import com.zhaowoba.adapter.WeclomePagerAdapter;
import com.zhaowoba.db.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 念阿郎 on 2018/5/16.
 * 引导页面的实现
 */

public class WelcomActivity extends Activity implements ViewPager.OnPageChangeListener {

    boolean isShow = false; // 是否显示引导界面
    private ViewPager mViewPager; // viewPager对象
    private LinearLayout linearLayout; // 装载小圆圈
    private List<View> views; // 装载引导界面的图片
    private ImageView[] mImageViews; // 小圆圈

    private WeclomePagerAdapter weclomePagerAdapter=null;

    //private Button login;//立即体验按钮
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载欢迎界面
        setContentView(R.layout.activity_welcome);
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        // 得到isShow数据
        isShow = PreferenceUtil.getBoolean(this, PreferenceUtil.SHOW_GUIDE);
        if (isShow) {//为true的话则进入 登陆界面
            jumpLogin();
        } else {//为false的话则进入欢迎
            //初始化控件
            initView();
        }
    }

    public void jumpLogin() {
        startActivity(new Intent(this, AdActivity.class));
        finish();
    }

    //初始化控件
    private void initView() {
        //图片
        mViewPager=(ViewPager)findViewById(R.id.guide_viewPager);
        linearLayout=(LinearLayout)findViewById(R.id.linear_layout);
        LayoutInflater inflater = LayoutInflater.from(this);
        //存放view的集合
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.welcome_page_one, null));
        views.add(inflater.inflate(R.layout.welcome_page_two, null));
        views.add(inflater.inflate(R.layout.welcome_page_three, null));
        //使用自定义的适配器加载view
        weclomePagerAdapter=new WeclomePagerAdapter(this,views);
        //设置圆点的大小
        mImageViews=new ImageView[views.size()];
        drawCircl(); // 画圆圈
        mViewPager.setAdapter(weclomePagerAdapter);
        //对viewPager进行监听
        mViewPager.setOnPageChangeListener(this);
    }
    // 画圆圈
    private void drawCircl() {
        int num = views.size();
        for (int i = 0; i < num; i++) {
            // 实例化每一个mImageViews
            mImageViews[i] = new ImageView(this);
            if (i == 0) {
                // 默认选中第一个对象
                mImageViews[i].setImageResource(R.drawable.dot_select);
            } else {
                mImageViews[i].setImageResource(R.drawable.dot_normal);
            }
            // 给每一个小圆圈设置间隔
            mImageViews[i].setPadding(15, 15, 15, 15);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            // 让每一个小圆圈在linearLayout都相互垂直
            params.gravity = Gravity.CENTER_VERTICAL;
            linearLayout.addView(mImageViews[i], params);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    // 页面有所改变，如果是当前页面，将小圆圈改为dot_select，其他页面则改为dot_normal
    @Override
    public void onPageSelected(int arg0) {
        for (int i = 0; i < mImageViews.length; i++) {
            if (arg0 != i) {
                mImageViews[i].setImageResource(R.drawable.dot_normal);
            } else {
                //选中
                mImageViews[arg0].setImageResource(R.drawable.dot_select);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
