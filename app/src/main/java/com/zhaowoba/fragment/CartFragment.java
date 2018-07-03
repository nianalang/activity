package com.zhaowoba.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.astuetz.PagerSlidingTabStrip;
import com.zhaowoba.R;
import com.zhaowoba.adapter.CartFragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;
public class CartFragment extends android.support.v4.app.Fragment{
    private android.support.v4.app.Fragment fragmentCartNoOrder=null;//未完成订单
    private android.support.v4.app.Fragment fragmentCartOrder=null;//完成订单
    List<Fragment> fragments=new ArrayList<Fragment>();
    //分隔栏的使用
    private PagerSlidingTabStrip tabCartStrip;
    //放置Fragment的ViewPager
    private ViewPager cartViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        initView(view);//绑定
        return view;
    }
    //绑定
    private void initView(View view) {
        tabCartStrip=view.findViewById(R.id.psts_cart_tab_strip);
        cartViewPager=view.findViewById(R.id.cart_view_pager);
        fragmentCartNoOrder=new CartFragmentNoOrder();
        fragmentCartOrder=new CartFragmentOrder();
        //将fragmnent加入网络
        fragments.add(fragmentCartNoOrder);
        fragments.add(fragmentCartOrder);
        //设置ViewPager每次预加载3个pager，也就是除当前pager外另外加载了三个pager，默认为1
        cartViewPager.setOffscreenPageLimit(1);
        //Fragment中在嵌套Fragment
        cartViewPager.setAdapter(new CartFragmentPagerAdapter(getChildFragmentManager(),fragments));
        //对ViewPager进行监听
        //对tabStrip进行设置
        setTabStrip(tabCartStrip);
        tabCartStrip.setViewPager(cartViewPager);
    }
    private void setTabStrip(PagerSlidingTabStrip tabStrip) {
        tabStrip.setDividerColor(getResources().getColor(R.color.white));
        tabStrip.setTextSize(45); //设置字体大小
        tabStrip.setTextColor(getResources().getColor(R.color.titleAllNormal));//设置字体颜色
        tabStrip.setIndicatorColor(getResources().getColor(R.color.titleAll));//滑动indicator的颜色
        tabStrip.setIndicatorHeight(5);//滑动indicator的高度
        tabStrip.setUnderlineHeight(2);
        tabStrip.setShouldExpand(true);//设为true时，每个tab的weight相同，默认false
    }
}
