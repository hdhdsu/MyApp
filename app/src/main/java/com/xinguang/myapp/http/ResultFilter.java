package com.xinguang.myapp.http;

import android.util.Log;

import rx.functions.Func1;

/**
 * Created by shangwf on 2017/5/16.
 */

public class ResultFilter<T> implements Func1<Result<T>, T> {


    @Override
    public T call(Result<T> response) {
//        if (IRetCode.successCode == response.getCode()) {//成功处理
//            return response.getResults();
//        } else {
//            throw new ApiException(response.getCode(), response.getError());
//        }
        Log.d("FUCK", response.toString() + ",,,,,," + response.getResults());
        return response.getResults();
    }
}
