package com.xinguang.myapp.http;

import android.util.Log;

import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit配置
 * Created by 14912 on 2018/3/21.
 */

public abstract class ApiBase {
    private static final String TAG = "ApiBase";
    public static Retrofit mRetrofit;

    public void init(boolean isDebug, OkHttpClient.Builder builder, String baseUrl) {

//        MockApiInterceptor mockApiInterceptor = new MockApiInterceptor(BaseApp.application);
//        mockApiInterceptor.addMockApiSuite(getMockApiSuite());
//        if (false) {
//            builder.addInterceptor(mockApiInterceptor);
//        }
            builder.addInterceptor(new LogInterceptor());

            mRetrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
            Log.d(TAG, "BaseUrl--->" + baseUrl);

        }

}
