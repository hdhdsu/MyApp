package com.xinguang.myapp.activity;

import android.os.Handler;

import com.xinguang.myapp.BuildConfig;
import com.xinguang.myapp.R;
import com.xinguang.myapp.utils.SpUtils;

/**
 * 闪屏页
 */
public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler();
    /**
     * 是否执行默认跳转
     */
    private boolean acquiescent;

    @Override
    protected int getPageLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //定时跳转
        acquiescent = true;
        timeToMain();
    }

    /**
     * 3秒后跳转，第一次安装的时候跳转到引导页，否则跳转到主界面
     */
    private void timeToMain() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (acquiescent) {
                    startLocation();
                }
            }
        },2000);
    }

    /**
     * 进入首页
     */
    private void startLocation() {
        //第一次安装的时候sp里面的版本号肯定小于build里面的版本号，所以跳转到启动页，在启动页里将版本号存起来
        if (SpUtils.getCurrentVersionCode(this)< BuildConfig.VERSION_CODE){
            startActivity(LaunchActivity.class);
            finish();
            return;
        }
        startActivity(MainActivity.class);
        finish();
    }

}
