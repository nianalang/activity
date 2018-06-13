package com.zhaowoba.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zhaowoba.R;

/**
 * Created by 念阿郎 on 2018/5/17.
 * 登陆和注册的处理
 */

public class LoginAndReagisterActivity extends Activity implements View.OnClickListener{

    private Button btnLogin;//登陆
    private Button btnRegister;//注册

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        initView();//绑定参数
    }
    //绑定参数
    private void initView() {
        //登陆
        btnLogin=findViewById(R.id.btn_login_register);

        //注册
        btnRegister=findViewById(R.id.btn_register_login);

        //绑定监听
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_register://登陆
                btnLogin.setTextColor(LoginAndReagisterActivity.this.getResources().getColor(R.color.white));
                //页面跳转
                Intent login=new Intent();
                login.setClass(LoginAndReagisterActivity.this,LoginActivity.class);
                btnLogin.setTextColor(LoginAndReagisterActivity.this.getResources().getColor(R.color.titleAll));
                startActivity(login);
                break;

            case R.id.btn_register_login://注册
                btnRegister.setTextColor(LoginAndReagisterActivity.this.getResources().getColor(R.color.white));
                //页面跳转
                Intent register=new Intent();
                register.setClass(LoginAndReagisterActivity.this,RegisterActivity.class);
                btnRegister.setTextColor(LoginAndReagisterActivity.this.getResources().getColor(R.color.titleAll));
                startActivity(register);
                break;
        }
    }
}
