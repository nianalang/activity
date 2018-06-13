package com.zhaowoba.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.zhaowoba.R;

/**
 * Created by 念阿郎 on 2018/5/21.
 * 对话框
 */

public class PublishDialog extends Dialog {

    private Button publish;//确定按钮
    private Button cancel;//取消按钮

    //送达的地点
    static public  EditText et_dialog_locate;
    //收件人信名
    static public EditText et_dialog_name;
    //收件人电话
    static public EditText et_dialog_phone;
    //代取价格
    static public EditText et_dialog_price;
    //快递的信息
    static public EditText et_dialog_info;

    private onPublishOnclickListener publishOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;

    public PublishDialog(Context context) {
        this(context, R.style.MyDialogStyleBottom);
    }

    public PublishDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_item);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (publishOnclickListener != null) {
                    publishOnclickListener.onPublishClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        publish = (Button) findViewById(R.id.tv_left);
        cancel = (Button) findViewById(R.id.tv_right);

        et_dialog_locate=(EditText) findViewById(R.id.et_dialog_locate);

        et_dialog_name=(EditText) findViewById(R.id.et_dialog_name);
        et_dialog_phone=(EditText) findViewById(R.id.et_dialog_phone);
        et_dialog_price=(EditText) findViewById(R.id.et_dialog_price);
        et_dialog_info=(EditText) findViewById(R.id.et_dialog_info);

    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onPublishOnclickListener {
        public void onPublishClick();
    }

    public interface onCancelOnclickListener {
        public void onCancelClick();
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     */
    public void setPublishOnclickListener(onPublishOnclickListener publishOnclickListener) {

        this.publishOnclickListener = publishOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     */
    public void setCancelOnclickListener(onCancelOnclickListener cancelOnclickListener) {

        this.cancelOnclickListener = cancelOnclickListener;
    }
}
