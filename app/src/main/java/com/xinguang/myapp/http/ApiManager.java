package com.xinguang.myapp.http;

import android.app.Activity;

import com.xinguang.myapp.common.Constants;
import com.xinguang.myapp.model.GankEntry;
import com.xinguang.myapp.model.GankResp;
import com.xinguang.myapp.model.Movie;
import com.xinguang.myapp.model.MovieSubject;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * 网络请求业务
 * Created by 14912 on 2017/12/28.
 */

public class ApiManager{

    public static void getMovie(Activity activity, int start,int count,BaseSubscriber.SuccessCallback<MovieSubject> mCallback){
        Api.getInstance().getApiService()
                .getTop250(start,count)
                .compose(RxUtils.rxNet())
                .subscribe(new BaseSubscriber<>(activity, mCallback,false));
    }

    public static void getGirl(Activity activity,BaseSubscriber.SuccessCallback<List<GankEntry>> mCallback){
        Api.getInstance().getApiService()
                .getGank()
                .compose(RxUtils.rxNet())
                .subscribe(new BaseSubscriber<List<GankEntry>>(activity, mCallback,true));
    }
//    /**
//     * 获取电影列表
//     * @param start
//     * @param count
//     * @return
//     */
//    public Observable<List<Movie>> getMovie(int start, int count){
//        return observe(mApiService.getTop250(start,count))
//                .map(new Func1<MovieSubject, List<Movie>>() {
//                    @Override
//                    public List<Movie> call(MovieSubject movieSubject) {
//                        return movieSubject.subjects;
//                    }
//                });
//    }
//
//    /**
//     * 获取干货列表
//     * @return
//     */
//    public Observable<List<GankEntry>> getGankList(){
//        return observe(mApiService.getGank(Constants.GANK_URL)).map(new Func1<GankResp, List<GankEntry>>() {
//            @Override
//            public List<GankEntry> call(GankResp gankResp) {
//                return gankResp.results;
//            }
//        });
//    }
}
