package com.xinguang.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xinguang.myapp.R;
import com.xinguang.myapp.manager.AppActivityManager;
import com.xinguang.myapp.utils.ArrayUtil;
import com.xinguang.myapp.utils.PermissionUtils2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 *
 * BaseActivity 中统一处理 Subscription . 防止内存泄漏
 * 权限管理
 * Created by lenghuo
 */

public class BaseActivity extends FragmentActivity implements EasyPermissions.PermissionCallbacks {
    private static final String TAG ="BaseActivity" ;
    protected BaseActivity context;
    private CompositeSubscription sCompositeSubscription ;
    private Map<String, Runnable> map = new HashMap<>();

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 权限申请成功
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Runnable runnable = map.get(ArrayUtil.toString(perms, "{", ",", "}"));
        if (runnable != null) {
            runnable.run();
        }
    }

    /**
     * 权限申请失败
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).setTitle("权限获取失败")
                    .setRationale("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
                    .setPositiveButton("好，去设置")
                    .build()
                    .show();
        }
    }

    /**
     * @param permissions 权限
     * @param data 说明
     * @param run 任务
     */
    protected void taskPermissions(String[] permissions, String data, Runnable run) {
        if (run == null) {
            PermissionUtils2.mPermissions(this, permissions);
            return;
        }
        map.put(ArrayUtil.toString(permissions, "{", ",", "}"), run);
        PermissionUtils2.mPermission(this, permissions, data, run);
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
