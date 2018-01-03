package com.xinguang.myapp.net;

/**
 * 异常处理类，将异常包装成一个 ExceptionStatus ,抛给上层统一处理
 * Created by lenghuo
 */

public class ExceptionStatus extends RuntimeException {
    private int errorCode;

    public ExceptionStatus(int errorCode, String message){
        super(message);
        errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
