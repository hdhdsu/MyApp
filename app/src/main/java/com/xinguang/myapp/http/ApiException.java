package com.xinguang.myapp.http;

import com.xinguang.myapp.utils.ToastUtils;

/**
 * 异常处理类，将异常包装成一个 ApiException ,抛给上层统一处理
 * Created by lenghuo
 */

public class ApiException extends RuntimeException {
    public final int retCode;
    public final String retMsg;
    private static boolean isShowToast = false;

    public ApiException(int retCode, String retMsg) {
        super(processCode(retCode, retMsg));
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    private static String processCode(int retCode, String retMsg) {
        isShowToast = true;
        switch (retCode) {
            case IRetCode.noLogin:
                //存储登陆信息
//                final String toLogin= StoreManager.getSingleton().getString(false, IWalpayConstants.TO_JUMP_LOGIN);
//                if(StringUtils.isEmpty(toLogin)||"no".equalsIgnoreCase(toLogin)){
//                    StoreManager.getSingleton().putString(false,IWalpayConstants.TO_JUMP_LOGIN,"yes");
//                    EventBus.getDefault().post(new NoLoginEvent());
//                }
                break;
            default:
                break;
        }

        if (isShowToast) ToastUtils.show(retMsg);
        return retMsg;
    }
}
