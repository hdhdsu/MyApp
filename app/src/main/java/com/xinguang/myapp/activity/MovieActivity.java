package com.xinguang.myapp.activity;

import android.Manifest;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.xinguang.myapp.R;
import com.xinguang.myapp.adapter.MovieAdapter;
import com.xinguang.myapp.model.Movie;
import com.xinguang.myapp.net.ExceptionStatus;
import com.xinguang.myapp.net.MyLoaders;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import rx.functions.Action1;

import static android.Manifest.permission.SEND_SMS;

/**
 * 电影列表
 */
public class MovieActivity extends BaseActivity implements View.OnClickListener{
    private MyLoaders mMovieLoader;
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    //相机，发送短信权限
    private String[] permissions = {Manifest.permission.CAMERA};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mMovieLoader = new MyLoaders();
        initView();
    }

    private void initView() {
        requestPermission();
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(R.string.movie_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new MovieDecoration());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mMovieAdapter = new MovieAdapter();
        mRecyclerView.setAdapter(mMovieAdapter);
        getMovieList();

    }

    /**
     * 请求相机的权限
     */
    private void requestPermission() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            Log.d("lenghuo","Already have permission, do the thing");
        } else {
            // Do not have permissions, request them now
            Log.d("lenghuo","Do not have permissions, request them now");

            EasyPermissions.requestPermissions(context, "111",1010, permissions);
        }
    }

    /**
     * 获取电影列表
     */
    private void getMovieList(){
        mMovieLoader.getMovie(0,10).subscribe(new Action1<List<Movie>>() {
            @Override
            public void call(List<Movie> movies) {
                //onNext
                mMovieAdapter.setMovies(movies);
                mMovieAdapter.notifyDataSetChanged();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                //onError
                Log.e("TAG","error message:"+throwable.getMessage());
                if(throwable instanceof ExceptionStatus){
                    ExceptionStatus fault = (ExceptionStatus) throwable;
                    if(fault.getErrorCode() == 404){
                        //错误处理
                    }else if(fault.getErrorCode() == 500){
                        //错误处理
                    }else if(fault.getErrorCode() == 501){
                        //错误处理
                    }
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        //getMovieRx();
    }

    public static class MovieDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0,0,0,20);
        }
    }
}
