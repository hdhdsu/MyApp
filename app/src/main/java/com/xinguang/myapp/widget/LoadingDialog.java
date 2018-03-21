package com.xinguang.myapp.widget;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xinguang.myapp.R;


public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    private TextView tvMsg;
    private boolean isCanBack=true;

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        setContentView(R.layout.loading_dialog);
        tvMsg = findViewById(R.id.id_tv_loadingmsg);
        setCanceledOnTouchOutside(false);
    }

    public static LoadingDialog createDialog(Context context) {
        return new LoadingDialog(context, R.style.LoadingDialog);
    }


    public void setMessage(String strMessage) {
        tvMsg.setVisibility(View.VISIBLE);
        tvMsg.setText(strMessage);
        tvMsg.invalidate();
    }

    @Override
    public void onBackPressed() {
        if(isCanBack){
            super.onBackPressed();
        }
    }

    public void setCanBack(boolean canBack) {
        isCanBack = canBack;
    }
}