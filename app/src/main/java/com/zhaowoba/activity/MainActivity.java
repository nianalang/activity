package com.zhaowoba.activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import com.zhaowoba.R;
import com.zhaowoba.fragment.NavigationFragment;
public class MainActivity extends FragmentActivity {
    /**
     * 沉浸式状态栏
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    //最大的Frgment
    private NavigationFragment navigationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //绑定xml配置文件
        setContentView(R.layout.activity_main);
        initView();//初始化控件
        //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        initState();
}
    //初始化控件
    private void initView() {
        //加载navigationFragment对象
        navigationFragment=new NavigationFragment();
        //开提交事务
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame,navigationFragment,"我是郎媛勤").commit();
    }
}
