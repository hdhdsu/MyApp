package com.xinguang.myapp.http;

import com.xinguang.myapp.BuildConfig;
import com.xinguang.myapp.common.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * retrofit 管理类
 * Created by 14912 on 2017/12/28.
 */

public class Api extends ApiBase{
    // 连接超时时间，默认10秒
    public static final int CONNECT_TIMEOUT = 5;
    public static final int CONNECT_LONG_TIMEOUT = 10;
    private static ApiService mApiService;
    private static OkHttpClient.Builder mBuilder;
    //单例
    private static class Holder {
        private final static Api instance = new Api();
    }
    public static Api getInstance() {
        return Holder.instance;
    }

    private Api(){
        init();
    }

    private void init() {
        mBuilder = new OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).addInterceptor(new CommonInterceptor());
        super.init(BuildConfig.DEBUG, mBuilder, getApiUrl());

        mApiService = mRetrofit.create(ApiService.class);
    }

    public static String getApiUrl() {
//        return IWalpayConstants.BASE_HTTPURL_DEVELOP;//开发环境地址
        return Constants.GANK_URL;//测试地址
//            return "http://xls-pay.yiqiguang.com/api/";//国内环境
    }

    public ApiService getApiService() {
        if (mApiService == null) {
            init();
        }
        return mApiService;
    }

    public static void connectTimeout(long connectTimeout) {
        mBuilder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
    }
}
