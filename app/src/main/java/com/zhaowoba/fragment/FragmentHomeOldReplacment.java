package com.zhaowoba.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhaowoba.R;

/**
 * Created by 念阿郎 on 2018/5/18.
 * 旧物置换
 */

public class FragmentHomeOldReplacment extends android.support.v4.app.Fragment {
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_old_replacement, container, false);
        textView = (TextView) view.findViewById(R.id.text);
        return view;
    }
}
