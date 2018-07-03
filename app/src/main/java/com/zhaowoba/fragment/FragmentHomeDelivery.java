package com.zhaowoba.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zhaowoba.R;
import com.zhaowoba.adapter.NormalRecyclerViewAdapter;
public class FragmentHomeDelivery extends android.support.v4.app.Fragment{
    //初始化RecycerView
    private RecyclerView rViewContent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_delivery, container, false);
        initView(view);//初始化控件
        return view;
    }
    private void initView(View view) {
        rViewContent = view.findViewById(R.id.rv_content);//绑定控件
        //设置布局管理器
        rViewContent.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于listview
        //添加分割线
        rViewContent.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        //设置adapter
        rViewContent.setAdapter(new NormalRecyclerViewAdapter(getActivity()));
    }
}
