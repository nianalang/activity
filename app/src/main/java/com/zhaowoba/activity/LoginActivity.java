package com.zhaowoba.activity;

/**
 * Created by depc on 2018/4/19.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhaowoba.R;
import com.zhaowoba.entity.HttpResult;
import com.zhaowoba.entity.ResultData;
import com.zhaowoba.entity.SimplifyUsers;
import com.zhaowoba.http.HttpAsyncTask;
import com.zhaowoba.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 登录界面的前端逻辑
 * @author 郎媛勤
 *
 */
public class LoginActivity extends Activity implements View.OnClickListener{

    public String urlLoginPath = "http://39.105.138.38:8080/zhaowoba/user/queryUser";

    /*
     * 用户名
     */
    private EditText loginUserName;
    /*
     * 密码
     */
    private EditText loginPassword;

    /*
       忘记密码
     */
    private TextView forgetPassword;

    /*
     * 登录按钮
     */
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();//初始化
        //注册EventBus
       // EventBus.getDefault().register(this);
    }

    /**
     * 初始化相关操作
     */
    private void init() {
        //绑定
        loginUserName=(EditText) findViewById(R.id.et_userName);
        loginPassword=(EditText) findViewById(R.id.et_password);
        forgetPassword=(TextView)findViewById(R.id.tv_forget);
        loginButton=(Button) findViewById(R.id.btn_login);
        //设置监听
        loginButton.setOnClickListener(this);
    }

    //登陆监听
    @Override
    public void onClick(View view) {
        login();
    }

    //登录处理
    private void login() {
        SimplifyUsers users=new SimplifyUsers();
        //得到用户名
        final String userName=loginUserName.getText().toString().trim();
        //得到密码
        final String password=loginPassword.getText().toString().trim();
        //判断用户名是否为空
       if(TextUtils.isEmpty(userName)){
            Toast.makeText(LoginActivity.this,"用户名不能为空!",Toast.LENGTH_SHORT).show();
        }else {
            users.setUsers_phone(userName);
            //判断密码是否为空
            if(TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
            }else {
                users.setUsers_password(password);

                //获取数据
                ResultData resultData=getData(users);
                if(resultData!=null){
                    if(1==resultData.getState()){//1代表登陆成功
                        //调到主activity
                        Intent intent=new Intent();
                        intent.setClass(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{//登陆失败
                        Toast.makeText(LoginActivity.this, resultData.getStateInfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    /*@Subscribe(threadMode = ThreadMode.MAIN)
    public void event(MessageEvent messageEvent) {

        if(messageEvent.equals("处理完成")){
            //获取数据
            ResultData resultData=httpAsyncTask.data;

            if(resultData!=null){
                if(1==resultData.getState()){//1代表登陆成功
                    //调到主activity
                    Intent intent=new Intent();
                    intent.setClass(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    // }
                }else{//登陆失败
                    Toast.makeText(LoginActivity.this, resultData.getStateInfo(), Toast.LENGTH_SHORT).show();
                }

            }
        }
    }*/
    ResultData resultData=null;

    public ResultData getData(SimplifyUsers user){

        //新建一个对象
        OkHttpClient client = new OkHttpClient();

        client.connectTimeoutMillis();

        RequestBody body = new FormBody.Builder()
                .add("users_phone", user.getUsers_phone())//请求的参数单
                .add("users_password", user.getUsers_password())
                .build();

        Request request = new Request.Builder().url(urlLoginPath).post(body).build(); //构建一个request对象

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Log.d("LoginActivity",  e.getMessage());
                Log.d("LoginActivity", "失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Gson gson = new Gson();//使用son进行解析
                HttpResult<ResultData> httpResult = gson.fromJson(responseData, new TypeToken<HttpResult<ResultData>>() {
                }.getType());
                resultData= httpResult.getData();
                Log.d("LoginActivity", resultData.getStateInfo()+"    hhhhhhhhhhhhhhh");
            }
        });
        return resultData;
    }


   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }*/

}
