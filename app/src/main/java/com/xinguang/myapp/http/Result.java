package com.xinguang.myapp.http;

/**
 * Created by shangwf on 2017/5/16.
 */

public class Result<T>{
//    private int code;
    private String error;
    private T results;

//    public int getCode() {
//        return code;
//    }

    public String getError() {
        return error;
    }

    public T getResults() {
        return results;
    }

//    public void setCode(int code) {
//        this.code = code;
//    }

    public void setError(String error) {
        this.error = error;
    }

    public void setResults(T results) {
        this.results = results;
    }

    //    public void setCode(int code) {
//        this.code = code;
//    }

//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
}
