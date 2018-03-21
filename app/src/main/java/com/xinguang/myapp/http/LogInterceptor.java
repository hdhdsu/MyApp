package com.xinguang.myapp.http;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by shangwf on 2017/4/28.
 */

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.d("接口请求：", request.url().toString());
//        Logger.d("接口参数：",request.body());


        Response response = chain.proceed(request);

        if (response != null && response.body() != null) {
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.d("接口响应：", content);
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
        return response;
    }
}
