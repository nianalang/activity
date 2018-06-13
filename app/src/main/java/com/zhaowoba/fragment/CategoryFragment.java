package com.zhaowoba.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhaowoba.R;
import com.zhaowoba.adapter.CategoryRecyerViewAdapter;
import com.zhaowoba.adapter.NormalRecyclerViewAdapter;
import com.zhaowoba.entity.HttpResult;
import com.zhaowoba.entity.PostDataDelivery;
import com.zhaowoba.utils.RecycleViewDivider;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 念阿郎 on 2018/5/8.
 */

public class CategoryFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public String urlLoginPath = "http://39.105.138.38:8080/zhaowoba/postDataDelivery/queryAllDataMessage";
    //初始化RecycerView
    private RecyclerView rViewList;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);//初始化控件
        return view;
    }

    private void initView(View view) {

        rViewList=view.findViewById(R.id.rc_list_view);//绑定控件

         swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        //为下拉刷新的设置四种颜色
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_green_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
       );




     /*   rViewList.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.red)));*/
        //设置adapter
        getData();


    }
    List<PostDataDelivery> postData=null;

    public void getData(){

        Log.d("LoginActivity", "getData() ");
        //新建一个对象
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .get()
                .url(urlLoginPath)
                .build(); //构建一个request对象

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("LoginActivity", "失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Gson gson = new Gson();//使用son进行解析

                Log.d("LoginActivity", responseData);
                HttpResult<List<PostDataDelivery>> httpResult =
                        gson.fromJson(responseData, new TypeToken<HttpResult<List<PostDataDelivery>>>() {
                }.getType());
                postData= httpResult.getData();
                Log.d("LoginActivity","kkkkkkkkkkkkkkkkkk");
                //设置布局管理器
                rViewList.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于listview
                //添加分割线
       /* rViewList.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));*/
                rViewList.addItemDecoration(new RecycleViewDivider(
                        getActivity(), LinearLayoutManager.VERTICAL, R.drawable.divider_mileage));
                CategoryRecyerViewAdapter adapter = new CategoryRecyerViewAdapter(getActivity(),postData);
                rViewList.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        //在onRefresh()方法中，我们设置了三秒钟刷新完成，即setRefreshing()中状态从true变为false。
        swipeRefreshLayout.setRefreshing(true);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                swipeRefreshLayout.setRefreshing(true);
//            }
//        }, 3000);
        getData();
    }
}
