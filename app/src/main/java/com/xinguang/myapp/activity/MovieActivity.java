package com.xinguang.myapp.activity;

import android.Manifest;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.xinguang.myapp.R;
import com.xinguang.myapp.adapter.MovieAdapter;
import com.xinguang.myapp.model.Movie;
import com.xinguang.myapp.http.ApiException;
import com.xinguang.myapp.http.ApiManager;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import rx.functions.Action1;

/**
 * 电影列表
 */
public class MovieActivity extends BaseActivity implements View.OnClickListener{
    private ApiManager mMovieLoader;
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    //相机，发送短信权限
    private String[] permissions = {Manifest.permission.CAMERA};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mMovieLoader = new ApiManager();
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
        ApiManager.getMovie(this,0,30,movieSubject -> {
            mMovieAdapter.setMovies(movieSubject.subjects);
            mMovieAdapter.notifyDataSetChanged();
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
