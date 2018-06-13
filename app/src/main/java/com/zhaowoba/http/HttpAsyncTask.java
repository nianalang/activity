package com.zhaowoba.http;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhaowoba.entity.HttpResult;
import com.zhaowoba.entity.ResultData;
import com.zhaowoba.entity.SimplifyUsers;
import com.zhaowoba.entity.User;
import com.zhaowoba.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 念阿郎 on 2018/5/18.
 */

public class HttpAsyncTask {
    public ResultData data;

    public String urlLoginPath = "http://192.168.43.8:8080/zhaowoba/user/queryUser";

    public HttpAsyncTask(SimplifyUsers users){
        new LoginAsyncTask().execute(users);
    }


    public class LoginAsyncTask extends AsyncTask<SimplifyUsers, Void, ResultData> {
        ResultData resultData=null;
        //执行预处理，它运行于UI线程，可以为后台任务做一些准备工作，比如绘制一个进度条控件。
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // 后台进程执行的具体计算在这里实现，
        @Override
        protected ResultData doInBackground(SimplifyUsers... simplifyUsers) {

            Log.d("LoginActivity","doInBackground()在执行");


            SimplifyUsers user=simplifyUsers[0];
            //新建一个对象
            OkHttpClient client = new OkHttpClient();

            client.connectTimeoutMillis();

            RequestBody body = new FormBody.Builder()
                    .add("users_phone", user.getUsers_phone())//请求的参数单
                    .add("users_password", user.getUsers_password())
                    .add("confirm_password", "")
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

        @Override
        protected void onPostExecute(ResultData resultData) {
            super.onPostExecute(resultData);
            Log.d("LoginActivity","onPostExecute()在执行");

           // Log.d("LoginActivity", resultData.getStateInfo()+"    lllllll");
            data=resultData;

            EventBus.getDefault().post(new MessageEvent("处理完成"));
            //String a1=resultData.getStateInfo();
            //String a=data.getStateInfo();
        }
    }
}

