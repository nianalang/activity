package com.zhaowoba.entity;

import java.io.Serializable;

/**
 * Created by 念阿郎 on 2018/5/15.
 */

public class ResultData implements Serializable{

    private int state;//状态

    private String stateInfo;//信息

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
