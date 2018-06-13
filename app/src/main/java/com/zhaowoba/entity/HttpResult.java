package com.zhaowoba.entity;

/**
 * 后端返回结果对象
 * @author depc
 *
 * @param <T>
 */
public class HttpResult<T> {

    /**
     * 表示请求结果的状态
     * 0：成功
     * 1:失败
     */
    private boolean success;
    /**
     * 成功或失败的信息
     */
    private boolean error;
    /**
     * 返回结果的数据
     */
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
