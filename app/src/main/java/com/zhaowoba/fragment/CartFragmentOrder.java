package com.zhaowoba.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhaowoba.R;
import com.zhaowoba.adapter.CartNoRecyerViewAdapter;
import com.zhaowoba.adapter.CartRecyerViewAdapter;
import com.zhaowoba.utils.RecycleViewDivider;

/**
 * Created by 念阿郎 on 2018/5/25.
 * 已完成订单
 */

public class CartFragmentOrder extends android.support.v4.app.Fragment{
    //初始化RecycerView
    private RecyclerView cartOrder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart_order, container, false);
        initView(view);//初始化控件
        return view;
    }

    private void initView(View view) {
        cartOrder=view.findViewById(R.id.rv_cart_order);//绑定控件
        //设置布局管理器
        cartOrder.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于listview
        //添加分割线
       /* rViewList.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));*/
        cartOrder.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, R.drawable.divider_mileage));

     /*   rViewList.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.red)));*/
        //设置adapter
        cartOrder.setAdapter(new CartNoRecyerViewAdapter(getActivity()));
    }
}
