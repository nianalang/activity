package com.zhaowoba.fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import com.astuetz.PagerSlidingTabStrip;
import com.zhaowoba.R;
import com.zhaowoba.adapter.HomeFragmentPagerAdapter;
import com.zhaowoba.adapter.HomePageAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
public class HomeFragment extends android.support.v4.app.Fragment implements ViewPager.OnPageChangeListener {
    private android.support.v4.app.Fragment fragmentHomeDelivery=null;
    private android.support.v4.app.Fragment fragmentHomeKettle=null;
    private android.support.v4.app.Fragment fragmentHomeBringMeal=null;
    private android.support.v4.app.Fragment fragmentHomeOldReplacment=null;
    List<android.support.v4.app.Fragment> fragments=new ArrayList<android.support.v4.app.Fragment>();
    /**
     * Page的数量
     */
    private static final int PAGER_NUM = 4;
    /**
     * ViewPager的对象
     */
    private ViewPager navigationbar;//导航栏
    private ViewPager homeGuideViewPager;//导航栏的图片
    private LinearLayout homeLinearLayout;
    private List<View> homeViews; // 装载引导界面的图片
    private ImageView[] homeMImageViews; // 小圆圈
    //设置当前 第几个图片 被选中
    private int autoCurrIndex = 0;
    private static final int UPTATE_VIEWPAGER = 0;
    private HomePageAdapter homePagerAdapter=null;
    private PagerSlidingTabStrip tabStrip;
    private Timer timer = new Timer(); //为了方便取消定时轮播，将 Timer 设为全局
    //定时轮播图片，需要在主线程里面修改 UI
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPTATE_VIEWPAGER:
                    if (msg.arg1 != 0) {
                        homeGuideViewPager.setCurrentItem(msg.arg1);
                    } else {
                        //false 当从末页调到首页是，不显示翻页动画效果，
                        homeGuideViewPager.setCurrentItem(msg.arg1, false);
                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);//初始化控件
        return view;
    }
    //初始化控件
    private void initView(View view) {
        navigationbar=view.findViewById(R.id.vp_navigation_bar);
        tabStrip=view.findViewById(R.id.psts_tab_strip);
        homeGuideViewPager=view.findViewById(R.id.home_guide_viewPager);
        homeLinearLayout=view.findViewById(R.id.home_linear_layout);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        //存放view的集合
        homeViews = new ArrayList<>();
        homeViews.add(inflater.inflate(R.layout.home_page_one, null));
        homeViews.add(inflater.inflate(R.layout.home_page_two, null));
        homeViews.add(inflater.inflate(R.layout.home_page_three, null));
        homeViews.add(inflater.inflate(R.layout.home_page_four, null));
        //使用自定义的适配器加载view
        homePagerAdapter=new HomePageAdapter(homeViews);
        //设置圆点的大小
        homeMImageViews=new ImageView[homeViews.size()];
        drawCircl(); // 画圆圈
        homeGuideViewPager.setAdapter(homePagerAdapter);
        homeGuideViewPager.setOnPageChangeListener(this);
        // 设置自动轮播图片，5s后执行，周期是5s
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = UPTATE_VIEWPAGER;
                if (autoCurrIndex == homeViews.size() - 1) {
                    autoCurrIndex = -1;
                }
                message.arg1 = autoCurrIndex + 1;
                mHandler.sendMessage(message);
            }
        }, 5000, 5000);
        fragmentHomeDelivery=new FragmentHomeDelivery();
        fragmentHomeKettle=new FragmentHomeKettle();
        fragmentHomeBringMeal=new FragmentHomeBringMeal();
        fragmentHomeOldReplacment=new FragmentHomeOldReplacment();
        fragments.add(fragmentHomeDelivery);
        fragments.add(fragmentHomeKettle);
        fragments.add(fragmentHomeBringMeal);
        fragments.add(fragmentHomeOldReplacment);
        //设置ViewPager每次预加载3个pager，也就是除当前pager外另外加载了三个pager，默认为1
        navigationbar.setOffscreenPageLimit(3);
        //Fragment中在嵌套Fragment
        navigationbar.setAdapter(new HomeFragmentPagerAdapter(getChildFragmentManager(),fragments));
        //对ViewPager进行监听
        navigationbar.setOnPageChangeListener(this);
        //对tabStrip进行设置
        setTabStrip(tabStrip);
        tabStrip.setViewPager(navigationbar);
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
    // 画圆圈
    private void drawCircl() {
        int num = homeViews.size();
        for (int i = 0; i < num; i++) {
            // 实例化每一个mImageViews
            homeMImageViews[i] = new ImageView(getActivity());
            if (i == 0) {
                // 默认选中第一个对象
                homeMImageViews[i].setImageResource(R.drawable.home_dot_select);
            } else {
                homeMImageViews[i].setImageResource(R.drawable.home_dot_normal);
            }
            // 给每一个小圆圈设置间隔
            homeMImageViews[i].setPadding(15, 15, 15, 15);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            // 让每一个小圆圈在linearLayout都相互垂直
            params.gravity = Gravity.CENTER_VERTICAL;
            homeLinearLayout.addView(homeMImageViews[i], params);
        }
    }
    /**
     * 对viewPager进行监听
     * @param position 当前页面，即你点击滑动的页面
     * @param positionOffset 当前页面偏移的百分比
     * @param positionOffsetPixels 当前页面偏移的像素位置
     */
    //当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用。
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    //此方法是页面跳转完后得到调用，arg0是你当前选中的页面的Position（位置编号）。
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < homeMImageViews.length; i++) {
            if (position != i) {
                homeMImageViews[i].setImageResource(R.drawable.home_dot_normal);
            } else {
                //选中
                homeMImageViews[position].setImageResource(R.drawable.home_dot_select);
            }
        }
        //设置全局变量，currentIndex为选中图标的 index
        autoCurrIndex=position;
    }
    //此方法是在状态改变的时候调用，其中arg0这个参数
    //有三种状态（0，1，2）。arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
    //当页面开始滑动的时候，三种状态的变化顺序为（1，2，0）
    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
