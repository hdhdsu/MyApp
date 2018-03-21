package com.xinguang.myapp.http;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shangwf on 2017/6/28.
 */

public class RxUtils {
    public static <T> Observable.Transformer<Result<T>, T> rxNet() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new ResultFilter<>());

    }
}