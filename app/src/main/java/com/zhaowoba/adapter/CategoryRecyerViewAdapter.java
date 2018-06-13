package com.zhaowoba.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhaowoba.R;
import com.zhaowoba.activity.LoginActivity;
import com.zhaowoba.activity.MainActivity;
import com.zhaowoba.entity.HttpResult;
import com.zhaowoba.entity.PostDataDelivery;
import com.zhaowoba.entity.ResultData;
import com.zhaowoba.entity.SimplifyUsers;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 念阿郎 on 2018/5/23.
 */

public class CategoryRecyerViewAdapter extends RecyclerView.Adapter<CategoryRecyerViewAdapter.ViewHolder> {


    private LayoutInflater mLayoutInflater;
    private Context mContext;

    List<PostDataDelivery>  postDataDelivery=null;

   // public static final int CONTENT = 1;
/*

    private String  nickname;//昵称
    private String time;//发布的时间


    private String item_locate_replace;//地点

    private String item_method_replace;//联系方式
    private String item_price_replace; //价格
    private String item_name_replace;//代取人姓名
    private String item_addrress_replace;//快递信息
*/


    /**
     * 当前类型
     */
   // public int currentType = CONTENT;
    public CategoryRecyerViewAdapter(Context context,List<PostDataDelivery>  postDataDelivery){
        //调取数据
        Log.d("LoginActivity", postDataDelivery.size()+"jjjjjjjjjj");
            this.postDataDelivery=postDataDelivery;
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);


    }
    //用于创建viewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.fragment_category_item, parent,false), mContext);
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(CategoryRecyerViewAdapter.ViewHolder holder, int position) {
        Log.d("LoginActivity", position+"dddddddddddddd");
        //得到位置
        PostDataDelivery delivery=postDataDelivery.get(position);
        //填充数据
        //快递信息
        holder.tv_item_addrress_replace.setText(delivery.getPost_data_delivery_message());
        //地址
        holder.tv_item_locate_replace.setText(delivery.getPost_data_delivery_address());
        //联系方式
        holder.tv_item_method_replace.setText(delivery.getPost_data_person_phone());
        //代取价格
        holder.tv_item_price_replace.setText(delivery.getPost_data_replace_price());
        //姓名
        holder.tv_item_name_replace.setText(delivery.getPost_data_delivery_name());
    }

    @Override
    public int getItemCount() {

        return postDataDelivery == null ? 0 : postDataDelivery.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView im_person_phonto;//头像


        public TextView tv_nickname;//昵称
        public TextView tv_time;//发布的时间


        public TextView tv_item_locate_replace;//地点

        public TextView tv_item_method_replace;//联系方式
        public TextView tv_item_price_replace; //价格
        public TextView tv_item_name_replace;//代取人姓名
        public TextView tv_item_addrress_replace;//快递信息

        public Button btn_replace_express;//求带
        public Button btn_cancel_express;//取消

        public Context mContext;

        public ViewHolder(View itemView, Context mContext) {
            super(itemView);
            im_person_phonto=itemView.findViewById(R.id.im_person_phonto);
            tv_nickname=itemView.findViewById(R.id.tv_nickname);

            tv_time=itemView.findViewById(R.id.tv_time);
            tv_item_locate_replace=itemView.findViewById(R.id.tv_item_locate_replace);
            tv_item_method_replace=itemView.findViewById(R.id.tv_item_method_replace);
            tv_item_price_replace=itemView.findViewById(R.id.tv_item_price_replace);
            tv_item_name_replace=itemView.findViewById(R.id.tv_item_name_replace);
            tv_item_addrress_replace=itemView.findViewById(R.id.tv_item_addrress_replace);
            //两个按钮
            btn_replace_express=itemView.findViewById(R.id.btn_replace_express);
            btn_cancel_express=itemView.findViewById(R.id.btn_cancel_express);


            //设置监听
            btn_replace_express.setOnClickListener(this);
            btn_cancel_express.setOnClickListener(this);

            this.mContext = mContext;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_replace_express://代取按钮
                    //执行操作

                    break;

                case R.id.btn_cancel_express://代取按钮
                    //执行操作

                    break;
            }

        }
    }

}
