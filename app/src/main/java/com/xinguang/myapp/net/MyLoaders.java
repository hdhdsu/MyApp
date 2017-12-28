package com.xinguang.myapp.net;

import com.xinguang.myapp.common.Constants;
import com.xinguang.myapp.model.GankEntry;
import com.xinguang.myapp.model.GankResp;
import com.xinguang.myapp.model.Movie;
import com.xinguang.myapp.model.MovieSubject;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by 14912 on 2017/12/28.
 */

public class MyLoaders extends ObjectLoader {

    //构造方法中生成services
    private MyServices mApiService;

    public MyLoaders() {
        //接口的创建
        this.mApiService = RetrofitServiceManager.getInstance().create(MyServices.class);
    }

    /**
     * 获取电影列表
     * @param start
     * @param count
     * @return
     */
    public Observable<List<Movie>> getMovie(int start, int count){
        return observe(mApiService.getTop250(start,count))
                .map(new Func1<MovieSubject, List<Movie>>() {
                    @Override
                    public List<Movie> call(MovieSubject movieSubject) {
                        return movieSubject.subjects;
                    }
                });
    }

    /**
     * 获取干货列表
     * @return
     */
    public Observable<List<GankEntry>> getGankList(){
        return observe(mApiService.getGank(Constants.GANK_URL)).map(new Func1<GankResp, List<GankEntry>>() {
            @Override
            public List<GankEntry> call(GankResp gankResp) {
                return gankResp.results;
            }
        });
    }
}
