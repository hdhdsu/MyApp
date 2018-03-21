package com.xinguang.myapp.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.xinguang.myapp.R;
import com.xinguang.myapp.adapter.GanKAdapter;
import com.xinguang.myapp.model.GankEntry;
import com.xinguang.myapp.http.ApiManager;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by zhouwei on 16/11/17.
 */

public class GankActivity extends BaseActivity {
    private ApiManager mGankLoader;
    private RecyclerView mRecyuclerView;
    private GanKAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gank_activity);
        mGankLoader = new ApiManager();
        initView();
        getGankList();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(R.string.award_list);
        mRecyuclerView = (RecyclerView) findViewById(R.id.gank_recycler_view);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyuclerView.setLayoutManager(manager);
        mRecyuclerView.addItemDecoration(new MyItemDecoration());
        mAdapter = new GanKAdapter();
        mRecyuclerView.setAdapter(mAdapter);
    }


    private void getGankList(){
        ApiManager.getGirl(this,gankResp -> {
            mAdapter.setData(gankResp);
                mAdapter.notifyDataSetChanged();
        });
    }

    public static class MyItemDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0,0,20,20);
        }
    }
}
