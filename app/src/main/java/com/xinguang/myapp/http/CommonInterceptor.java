package com.xinguang.myapp.http;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.reflect.Field;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shangwf on 2017/4/28.
 */

public class CommonInterceptor implements Interceptor {
    private final String EXECUTE_BARCODE_TRADE = "trade/execute_barcode_trade";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        setConnectTimeout(oldRequest);

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
//                .addHeader("version",AppUtils.getVersionCode(BaseApp.application)+"")
                .addHeader("OS","android")
                .addHeader("ip","")
//                .addHeader("mac", DeviceUtils.getMacAddress(BaseApp.application))
//                .addHeader("Authorization",StoreManager.getSingleton().getString(false,IWalpayConstants.AUTHORIZATION,""))
                .method(oldRequest.method(), oldRequest.body())
                .url(getHttpUrl(oldRequest))
                .build();
        return chain.proceed(newRequest);
    }

    @NonNull
    private HttpUrl getHttpUrl(Request oldRequest) {

        // 添加新的参数
        HttpUrl currentEnVironmentHttpUrl = HttpUrl.parse(Api.getApiUrl());
        return oldRequest
                .url()
                .newBuilder()
                .scheme(currentEnVironmentHttpUrl.scheme())
                .host(currentEnVironmentHttpUrl.host())
                .port(currentEnVironmentHttpUrl.port())
                .build();
    }


    private void setConnectTimeout(Request oldRequest) {
        //动态设置超时时间
        final String questUrl = oldRequest.url().url().toString();
        try {
            Field callFactoryField = ApiBase.mRetrofit.getClass().getDeclaredField("callFactory");
            callFactoryField.setAccessible(true);
            OkHttpClient client = (OkHttpClient) callFactoryField.get(ApiBase.mRetrofit);

            Field connectTimeoutField = client.getClass().getDeclaredField("connectTimeout");
            connectTimeoutField.setAccessible(true);
            if (questUrl.contains(EXECUTE_BARCODE_TRADE)) {
                connectTimeoutField.setInt(client, Api.CONNECT_LONG_TIMEOUT * 1000);
            } else {
                connectTimeoutField.setInt(client, Api.CONNECT_TIMEOUT * 1000);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
