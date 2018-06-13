package com.zhaowoba.utils;

/**
 * Created by 念阿郎 on 2018/5/22.
 * 定义消息类事件
 */

public class MessageEvent {

    private String message;

    public  MessageEvent(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
