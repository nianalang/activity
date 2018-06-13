package com.zhaowoba.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.zhaowoba.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 念阿郎 on 2018/5/16.
 * 广告页
 */

public class AdActivity extends Activity{
    private int recLen = 5;//跳过倒计时提示5秒
    private Button skipButton;

    Timer timer = new Timer();
    private Handler handler;
    private TimerTask task;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        initView();

        task= new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() { // UI thread
                    @Override
                    public void run() {
                        recLen--;
                        skipButton.setText("跳过 " + recLen);
                        if (recLen < 0) {
                            timer.cancel();
                            skipButton.setVisibility(View.GONE);//倒计时到0隐藏字体
                        }
                    }
                });
            }
        };

        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        /**
         * 正常情况下不点击跳过
         */
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(AdActivity.this, LoginAndReagisterActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);//延迟5S后发送handler信息
    }

    //初始化控件
    private void initView() {

        skipButton=(Button)findViewById(R.id.bt_skip);
       /* skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  判断以前是否登陆过
               //从闪屏界面跳转到首界面
                Intent intent = new Intent(AdActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });*/
    }

}
