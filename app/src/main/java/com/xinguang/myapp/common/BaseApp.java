package com.xinguang.myapp.common;

import android.app.Application;


/**
 * Created by shangwf on 2017/4/29.
 */

public abstract class BaseApp extends Application{
    public static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
