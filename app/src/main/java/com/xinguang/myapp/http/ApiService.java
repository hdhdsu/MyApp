package com.xinguang.myapp.http;

import com.xinguang.myapp.model.GankEntry;
import com.xinguang.myapp.model.GankResp;
import com.xinguang.myapp.model.MovieSubject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * retrofit的接口
 * Created by 14912 on 2017/12/28.
 */
public interface ApiService {

    //获取豆瓣Top250 榜单
    @GET("top250")
    Observable<Result<MovieSubject>> getTop250(@Query("start") int start, @Query("count")int count);

    @GET("福利/50/1")
    Observable<Result<List<GankEntry>>> getGank(/*, @Path("count")int count,@Path("page")int page*/);

}
