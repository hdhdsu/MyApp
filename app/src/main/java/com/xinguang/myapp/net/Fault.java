package com.xinguang.myapp.net;

/**
 * 异常处理类，将异常包装成一个 Fault ,抛给上层统一处理
 * Created by lenghuo
 */

public class Fault extends RuntimeException {
    private int errorCode;

    public Fault(int errorCode,String message){
        super(message);
        errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
