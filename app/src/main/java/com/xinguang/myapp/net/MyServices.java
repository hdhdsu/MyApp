package com.xinguang.myapp.net;

import com.xinguang.myapp.model.GankResp;
import com.xinguang.myapp.model.MovieSubject;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 网络请求
 * Created by 14912 on 2017/12/28.
 */
public interface MyServices {

    //获取豆瓣Top250 榜单
    @GET("top250")
    Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);

    @GET
    Observable<GankResp> getGank(@Url String url/*, @Path("count")int count,@Path("page")int page*/);

}
