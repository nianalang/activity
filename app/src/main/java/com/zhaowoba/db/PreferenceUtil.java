package com.zhaowoba.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 念阿郎 on 2018/5/16.
 */

public class PreferenceUtil {
    // 是否显示欢迎界面,true表示显示，false表示不显示
    public static final String SHOW_GUIDE = "showguide";

    // 保存到Preference
    public static void setBoolean(Context context, String key, boolean value) {
        // 得到SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences(
                "preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    // 从Preference取出数据
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(
                "preference", Context.MODE_PRIVATE);
        // 返回key值，key值默认值是false
        return preferences.getBoolean(key, false);
    }
}
