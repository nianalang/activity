package com.zhaowoba.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.zhaowoba.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 念阿郎 on 2018/5/26.
 */


public class CartNoRecyerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;

  /*  String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");

    *//**
     * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
     *//*
    String sign = sign(orderInfo);
    try {
        *//**
         * 仅需对sign 做URL编码
         *//*
        sign = URLEncoder.encode(sign, "UTF-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }

    *//**
     * 完整的符合支付宝参数规范的订单信息
     *//*
    final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

    Runnable payRunnable = new Runnable() {

        @Override
        public void run() {
            // 构造PayTask 对象
            PayTask alipay = new PayTask(MainActivity.this);
            // 调用支付接口，获取支付结果
            String result = alipay.pay(payInfo, true);

            Message msg = new Message();
            msg.what = SDK_PAY_FLAG;
            msg.obj = result;
            mHandler.sendMessage(msg);
        }
    };

    // 必须异步调用
    Thread payThread = new Thread(payRunnable);
    payThread.start();


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    *//**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     *//*
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(MainActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

*/






    public static final int CONTENT = 1;
    /**
     * 当前类型
     */
    public int currentType = CONTENT;

    public CartNoRecyerViewAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CONTENT) {
            return new CartNoViewHolder(mLayoutInflater.inflate(R.layout.fragment_cart_no_order_item, parent,false), mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == CONTENT) {
            CartNoViewHolder cartNoViewHolder = (CartNoViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    /**
     * 根据位置得到类型-系统调用
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case CONTENT:
                currentType = CONTENT;
                break;
        }
        return currentType;
    }



    class CartNoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public Context mContext;

        public CartNoViewHolder(View itemView, Context mContext) {
            super(itemView);

            this.mContext = mContext;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){

            }

        }
    }
}
