package com.zhaowoba.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zhaowoba.R;
/**
 * 主Fragment
 */
public class NavigationFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    /**
     * 首页Fragment
     */
    private HomeFragment homeFragment;
    /**
     * 分类Fragment
     */
    private CategoryFragment categoryFragment;
    /**
     * 购物车Fragment
     */
    private CartFragment cartFragment;
    /**
     * 我的Fragment
     */
    private PersonalFragment personalFragment;
    private LinearLayout tabHome;
    private LinearLayout tabCategory;
    private LinearLayout tabCart;
    private LinearLayout tabPersonal;
    private ImageButton tabHomeBtn;
    private ImageButton tabCategoryBtn;
    private ImageButton tabCartBtn;
    private ImageButton tabPersonalBtn;
    private android.support.v4.app.FragmentManager fragmentManager;
    /*
    首页的发现标题
   */
    private TextView titleDiscover;

    /**
     * 首页的标题
     */
    private TextView titleHome;
    /**
     * 首页的订单标题
     */
    private TextView titleOrder;
    /**
     * 首页的我的标题
     */
    private TextView titleMy;
    private int curredtId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        initViews(view);
        setTabSeletion(R.id.tab_home);
        return view;
    }
    //初始化试图对象
    private void initViews(View view) {
        //主界面
        tabHome = view.findViewById(R.id.tab_home);
        tabCategory = view.findViewById(R.id.tab_category);
        tabCart = view.findViewById(R.id.tab_cart);
        tabPersonal = view.findViewById(R.id.tab_personal);
        tabHomeBtn = view.findViewById(R.id.tab_home_btn);
        tabCategoryBtn = view.findViewById(R.id.tab_category_btn);
        tabCartBtn = view.findViewById(R.id.tab_cart_btn);
        tabPersonalBtn = view.findViewById(R.id.tab_personal_btn);
        titleHome = view.findViewById(R.id.tv_title_home);
        titleDiscover = view.findViewById(R.id.tv_title_category);
        titleOrder = view.findViewById(R.id.tv_title_cart);
        titleMy = view.findViewById(R.id.tv_title_personal);
        //设置监听事件
        tabHome.setOnClickListener(this);
        tabCategory.setOnClickListener(this);
        tabCart.setOnClickListener(this);
        tabPersonal.setOnClickListener(this);
        //得到fragment管理对象
        fragmentManager = getFragmentManager();
    }

    @Override
    public void onClick(View v) {
        //设置Tab是否选中
        setTabSeletion(v.getId());
    }
    private void setTabSeletion(int id) {
        //重置tab选中状态
        tabHomeBtn.setImageResource(R.drawable.tab_home_normol);
        titleHome.setTextColor(getActivity().getResources().getColor(R.color.titleAllNormal));
        tabCategoryBtn.setImageResource(R.drawable.tab_category_normol);
        titleDiscover.setTextColor(getActivity().getResources().getColor(R.color.titleAllNormal));
        tabCartBtn.setImageResource(R.drawable.tab_cart_normol);
        titleOrder.setTextColor(getActivity().getResources().getColor(R.color.titleAllNormal));
        tabPersonalBtn.setImageResource(R.drawable.tab_personal_normol);
        titleMy.setTextColor(getActivity().getResources().getColor(R.color.titleAllNormal));
        //开启一个事务
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //隐藏所有的Fragment
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (categoryFragment != null) {
            fragmentTransaction.hide(categoryFragment);
        }
        if (cartFragment != null) {
            fragmentTransaction.hide(cartFragment);
        }
        if (personalFragment != null) {
            fragmentTransaction.hide(personalFragment);
        }
        //根据Tabid执行不同的操作
        switch (id) {
            case R.id.tab_home:
                tabHomeBtn.setImageResource(R.drawable.tab_home_focus);
                titleHome.setTextColor(getActivity().getResources().getColor(R.color.titleAll));
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case R.id.tab_category:
                tabCategoryBtn.setImageResource(R.drawable.tab_category_focus);
                titleDiscover.setTextColor(getActivity().getResources().getColor(R.color.titleAll));
                if (categoryFragment == null) {
                    categoryFragment = new CategoryFragment();
                    fragmentTransaction.add(R.id.content, categoryFragment);
                } else {
                    fragmentTransaction.show(categoryFragment);
                }
                break;
            case R.id.tab_cart:
                tabCartBtn.setImageResource(R.drawable.tab_cart_focus);
                titleOrder.setTextColor(getActivity().getResources().getColor(R.color.titleAll));
                if (cartFragment == null) {
                    cartFragment = new CartFragment();
                    fragmentTransaction.add(R.id.content, cartFragment);
                } else {
                    fragmentTransaction.show(cartFragment);
                }
                break;
            case R.id.tab_personal:
                tabPersonalBtn.setImageResource(R.drawable.tab_personal_focus);
                titleMy.setTextColor(getActivity().getResources().getColor(R.color.titleAll));
                if (personalFragment == null) {
                    personalFragment = new PersonalFragment();
                    fragmentTransaction.add(R.id.content, personalFragment);
                } else {
                    fragmentTransaction.show(personalFragment);
                }
                break;
        }
        //提交
        fragmentTransaction.commit();
        curredtId = id;
    }
}
