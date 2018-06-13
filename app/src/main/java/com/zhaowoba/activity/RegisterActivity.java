package com.zhaowoba.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhaowoba.R;
import com.zhaowoba.entity.HttpResult;
import com.zhaowoba.entity.ResultData;
import com.zhaowoba.entity.User;

import java.io.IOException;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 念阿郎 on 2018/5/17.
 * 注册界面
 */

public class RegisterActivity extends Activity implements View.OnClickListener {

    public String urlLoginPath = "http://39.105.138.38:8080/zhaowoba/user/insertUsers";
    ResultData resultData = null;
    private int countSeconds = 60;//倒计时秒数
    private EditText userName = null;//用户名
    private EditText securityCode;//验证码l
    private EditText password = null;//密码
    private EditText confirmPass = null;//确认密码
    private CountDownTimer countDownTimer;
    private Button btnSecurityCode;
    //UI线程处理handler的消息,这个handler用于接收短信回调事件
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                btnSecurityCode.setText("重新发送(" + countSeconds + ")");
            } else if (msg.what == -8) {
                btnSecurityCode.setText("获取验证码");
                btnSecurityCode.setClickable(true); // 设置可点击
                countSeconds = 60;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回MainActivity,然后提示
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();
                        // 验证成功后跳转主界面

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "验证码已经发送",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        }
    };
    private Button btnRegister;

    //判断一个字符串的位数
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    public static boolean isMobileNO(String mobileNums) {
       /*
        * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
        * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        */
        String telRegex = "[1][3578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }
    //验证手机格式

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.antivity_register);
        initView();//数据绑定

        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;//事件类型
                msg.arg2 = result;//操作结果，为SMSSDK.RESULT_COMPLETE表示成功，为SMSSDK.RESULT_ERROR表示失败
                msg.obj = data;//操作结果，如果result=SMSSDK.RESULT_ERROR，则类型为Throwable，如果result=SMSSDK.RESULT_COMPLETE需根据event判断：
                mHandler.sendMessage(msg);//发送消息，等待UI处理
            }

        };

        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    private void initView() {
        userName = findViewById(R.id.et_login);
        securityCode = findViewById(R.id.et_confirm_code);
        password = findViewById(R.id.et_password);
        confirmPass = findViewById(R.id.et_confirm_password);
        //获取验证码按钮
        btnSecurityCode = findViewById(R.id.bt_confirm_code);
        btnSecurityCode.setOnClickListener(this);

        btnRegister = findViewById(R.id.rtn_register);
        btnRegister.setOnClickListener(this);
    }

    //判断手机号码是否合理
    public boolean judgePhoneNumss(String number) {
        if (isMatchLength(number, 11)
                && isMobileNO(number)) {
            return true;
        }
        //shortToast(getResources().getString(R.string.input_phone_error));
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    @Override
    public void onClick(View v) {
        //获取电话号码
        String phoneNums = userName.getText().toString();


        switch (v.getId()) {
            case R.id.bt_confirm_code://验证码
                if (!judgePhoneNumss(phoneNums)) {// 判断输入号码是否正确
                    return;
                }
                SMSSDK.getVerificationCode("86", phoneNums);                 // 调用sdk发送短信验证
                btnSecurityCode.setClickable(false);// 设置按钮不可点击 显示倒计时
                btnSecurityCode.setText("重新发送(" + countSeconds + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (countSeconds = 60; countSeconds > 0; countSeconds--) {
                            mHandler.sendEmptyMessage(-9);
                            if (countSeconds <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);// 线程休眠实现读秒功能
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        mHandler.sendEmptyMessage(-8);// 在30秒后重新显示为获取验证码
                    }
                }).start();
                break;
            case R.id.rtn_register://注册
                getRegister();
                break;
        }
    }

    private void getRegister() {
        User users = new User();
        //得到用户名
        final String userName1 = userName.getText().toString().trim();
        //得到密码
        final String password1 = password.getText().toString().trim();
        final String confirmPass1 = confirmPass.getText().toString().trim();
        //判断用户名是否为空
        if (TextUtils.isEmpty(userName1)) {
            Toast.makeText(RegisterActivity.this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
        } else {
            users.setUsers_phone(userName1);
            //判断密码是否为空
            if (TextUtils.isEmpty(password1)) {
                Toast.makeText(RegisterActivity.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
            } else {
                if (TextUtils.isEmpty(confirmPass1)) {
                    Toast.makeText(RegisterActivity.this, "确认密码不能为空!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(password1.equals(confirmPass1))) {
                        Toast.makeText(RegisterActivity.this, "两次输入的密码不同!", Toast.LENGTH_SHORT).show();
                    } else {
                        //获取数据
                        ResultData resultData = getDataRe(users);
                        if (resultData != null) {
                            if (4 == resultData.getState()) {//1代表注册成功
                                //调到主activity
                                Intent intent = new Intent();
                                intent.setClass(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {//登陆失败
                                Toast.makeText(RegisterActivity.this, resultData.getStateInfo(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        }
    }

    private ResultData getDataRe(User user) {
        Log.d("LoginActivity", "getDataRe()000000000000000");
        //新建一个对象
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("users_phone", user.getUsers_phone())//请求的参数单
                .add("users_password", user.getUsers_password())
                .add("users_sex", "")
                .add("", "")
                .build();

        Request request = new Request.Builder().url(urlLoginPath).post(body).build(); //构建一个request对象

        Call call = client.newCall(request);

        Log.d("LoginActivity", "失败hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Log.d("LoginActivity",  e.getMessage());
                Log.d("LoginActivity", "失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();//使用son进行解析
                HttpResult<ResultData> httpResult = gson.fromJson(responseData, new TypeToken<HttpResult<ResultData>>() {
                }.getType());
                resultData = httpResult.getData();
                Log.d("LoginActivity", resultData.getStateInfo() + "    hhhhhhhhhhhhhhh");
            }
        });
        return resultData;
    }
}