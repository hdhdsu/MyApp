package com.xinguang.myapp.http;

import android.app.Activity;

import com.xinguang.myapp.R;
import com.xinguang.myapp.common.BaseApp;
import com.xinguang.myapp.utils.NetWorkUtils;
import com.xinguang.myapp.utils.ResUtils;
import com.xinguang.myapp.utils.ToastUtils;
import com.xinguang.myapp.widget.LoadingDialog;

import rx.Subscriber;

/**
 * Created by shangwf on 2017/5/16.
 */

public class BaseSubscriber<T> extends Subscriber<T> {

    private LoadingDialog mLoadingDialog;
    private final Activity mActivity;
    private final SuccessCallback<T> mCallback;
    private ErrorCallback mErrorCallback;
    private boolean mIsShowLoading = true;

    public BaseSubscriber(Activity activity, SuccessCallback<T> mCallback) {
        this.mActivity = activity;
        this.mCallback = mCallback;
    }

    public BaseSubscriber(Activity activity, SuccessCallback<T> mCallback, boolean isShowLoading) {
        this.mActivity = activity;
        this.mCallback = mCallback;
        this.mIsShowLoading = isShowLoading;
    }

    public BaseSubscriber(Activity activity, SuccessCallback<T> mCallback, ErrorCallback errorCallback) {
        this.mActivity = activity;
        this.mCallback = mCallback;
        this.mErrorCallback = errorCallback;
    }

    @Override
    public void onStart() {
        //检查网络
        if (!NetWorkUtils.isConnectedByState(mActivity)) {

            ToastUtils.show(ResUtils.getText(mActivity, R.string.net_not_link));
            return;
        }
        if (null != mActivity && isShowLoading()) {
            mLoadingDialog = LoadingDialog.createDialog(mActivity);
            mLoadingDialog.show();
        }
    }

    @Override
    public void onCompleted() {
        dismissLoadingDialog();
    }

    @Override
    public void onNext(T t) {
        dismissLoadingDialog();
        mCallback.callback(t);
    }

    @Override
    public void onError(Throwable e) {
        try {
            if (!(e instanceof ApiException)) {
                e.printStackTrace();
                if (isShowNetErrorToast()) {
                    ToastUtils.show(ResUtils.getText(BaseApp.application, R.string.net_error));
                }
            }
            dismissLoadingDialog();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        if (null != mErrorCallback) {
            mErrorCallback.callback(e);
        }

    }

    private void dismissLoadingDialog() {
        if (null != mActivity && isShowLoading() && null != mLoadingDialog && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    public boolean isShowLoading() {
        return mIsShowLoading;
    }

    public boolean isShowNetErrorToast() {
        return true;
    }

    public interface SuccessCallback<T> {
        void callback(T t);
    }

    public interface ErrorCallback {
        void callback(Throwable t);
    }
}
