package com.zhaowoba.entity;

/**
 * 用户表
 * Created by depc on 2018/4/19.
 */

public class User {

    /*
     * 用户电话号码
     */
    private String users_phone;
    /*
     * 用户密码
     */
    private String users_password;

    /*
     * 用户性别
     */
    private String users_sex;


    public String getUsers_phone() {
        return users_phone;
    }
    public void setUsers_phone(String users_phone) {
        this.users_phone = users_phone;
    }
    public String getUsers_password() {
        return users_password;
    }
    public void setUsers_password(String users_password) {
        this.users_password = users_password;
    }

    public String getUsers_sex() {
        return users_sex;
    }
    public void setUsers_sex(String users_sex) {
        this.users_sex = users_sex;
    }
}
