package com.zhaowoba.entity;

import java.io.Serializable;

/**
 * 用户名简化
 * Created by 念阿郎 on 2018/4/29.
 */

public class SimplifyUsers implements Serializable{
    /*
	 * 用户号码
	 */
    private String users_phone;
    /*
     * 用户密码|新密码
     */
    private String users_password;

    /*
     * 确认密码
     */
    private String confirm_password;

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

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    @Override
    public String toString() {
        return "SimplifyUsers [users_phone=" + users_phone + ", users_password=" + users_password
                + ", confirm_password=" + confirm_password + "]";
    }

}
