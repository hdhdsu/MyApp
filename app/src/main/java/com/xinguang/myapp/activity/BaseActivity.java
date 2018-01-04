package com.xinguang.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xinguang.myapp.R;
import com.xinguang.myapp.manager.AppActivityManager;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 *
 * BaseActivity 中统一处理 Subscription . 防止内存泄漏
 * 权限管理
 * Created by lenghuo
 */

public class BaseActivity extends FragmentActivity {
    private static final String TAG ="BaseActivity" ;
    protected BaseActivity context;
    private CompositeSubscription sCompositeSubscription ;

    /**
     * 设置页面布局ID
     *
     * @return
     */
    protected int getPageLayoutID() {
        return 0;
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 初始化页面控件
     */
    protected void initView(Bundle savedInstanceState) {
    }

    /**
     * 初始化控件监听器
     */
    protected void initViewListener() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加activity
        AppActivityManager.addActivity(this);
        context = this;
        if(sCompositeSubscription == null || sCompositeSubscription.isUnsubscribed()){
            sCompositeSubscription = new CompositeSubscription();
        }

        int layout = getPageLayoutID();
        if (getPageLayoutID() != 0) {
            setContentView(layout);
            initView(savedInstanceState);
            initData();
            initViewListener();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 添加Subscription
     * @param subscription
     */
    public void addSubscription(Subscription subscription){
        Log.d(TAG,"add subscription");
        sCompositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sCompositeSubscription!=null){
            Log.d(TAG,"base activity unscbscribe");
            sCompositeSubscription.unsubscribe();
        }
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
    //这里还可以重载一个activity跳转动画

    public void startActivityForResult(Class clazz, int code) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, code);
    }

}
