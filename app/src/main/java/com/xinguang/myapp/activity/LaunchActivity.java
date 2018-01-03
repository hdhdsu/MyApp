package com.xinguang.myapp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.xinguang.myapp.BuildConfig;
import com.xinguang.myapp.R;
import com.xinguang.myapp.adapter.GuideViewPagerAdapter;
import com.xinguang.myapp.utils.SpUtils;

/**
 * 引导页
 * Created by 14912 on 2018/1/3.
 */

public class LaunchActivity extends BaseActivity implements GuideViewPagerAdapter.GuideClickLinstener{

    private ViewPager viewPager;
    private GuideViewPagerAdapter mAdapter;

    @Override
    protected int getPageLayoutID() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initData() {
        //版本号存下来
        SpUtils.saveCurrentVersionCode(this, BuildConfig.VERSION_CODE);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        viewPager = findViewById(R.id.viewpager);
        mAdapter = new GuideViewPagerAdapter(this);
        viewPager.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager = null;
        mAdapter = null;
    }

    @Override
    public void click() {
        //直接进入主页
        startActivity(MainActivity.class);
        //finish掉引导页
        finish();
    }
}
