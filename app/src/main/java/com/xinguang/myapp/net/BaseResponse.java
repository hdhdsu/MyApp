package com.xinguang.myapp.net;

/**
 *
 * 网络请求结果 基类
 * Created by lenghuo
 */

public class BaseResponse<T> {
    public int status;
    public String message;

    public T data;

    public boolean isSuccess(){
        return status == 200;
    }
}
