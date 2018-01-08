package com.xinguang.myapp.utils;

import android.app.Activity;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 权限工具类
 */

public class PermissionUtils2 {
    static String TAG = "PermissionUtils2";
    private static final int WRITE_CONTACTS = 0x001;
    private static final int CALL_PHONE = 0x002;
    private static final int GET_ACCOUNTS = 0x003;
    private static final int READ_CONTACTS = 0x004;


    /**
     * 申请多条权限
     *
     * @param perms 权限数组
     */
    public static void mPermissions(Activity contexts, String[] perms) {
        mPermission(contexts, perms, null, null);
    }

    /**
     * 执行任务时 检测权限
     *
     * @param perms 权限数组
     * @param data 提示用户语
     * @param runnable 有权限时的任务  6.0以下默认通过
     */
    public static void mPermission(Activity contexts, String[] perms, String data,
            Runnable runnable) {
        //1、检测权限
        if (EasyPermissions.hasPermissions(contexts, perms)) {
            if (runnable != null) {
                runnable.run();
            }
        } else {//2、申请权限
                EasyPermissions.requestPermissions(contexts, data == null ? "需要必要的权限才能正常使用，是否允许" : data,
                        WRITE_CONTACTS, perms);
        }
    }
}
