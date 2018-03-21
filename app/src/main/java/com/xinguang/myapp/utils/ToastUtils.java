package com.xinguang.myapp.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xinguang.myapp.R;
import com.xinguang.myapp.common.BaseApp;

/**
 * Created by shangwf on 2017/4/29.
 */

public class ToastUtils {
    private static Toast mToast;
    private static int mToastViewId = -1;

    public static void show(String message) {
        showToast(message, message.length() > 10 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
    }

    public static void showLong(String mssage) {
        showToast(mssage, Toast.LENGTH_LONG);
    }

    public static void showToast(String message, int duration) {

        if (TextUtils.isEmpty(message)) return;
        if (mToast == null) {
            mToast = Toast.makeText(BaseApp.application, message, duration);
            if (mToastViewId != -1) {
                LayoutInflater inflate = (LayoutInflater)
                        BaseApp.application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflate.inflate(mToastViewId, null);
                TextView toast = v.findViewById(R.id.toast);
                toast.setText(message);
                mToast.setView(v);
            }

        } else {
            ((TextView) mToast.getView().findViewById(R.id.toast)).setText(message);
            mToast.setDuration(duration);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);

        mToast.show();
    }


    public static void setmToastViewId(int mToastViewId) {
        ToastUtils.mToastViewId = mToastViewId;
    }
}
