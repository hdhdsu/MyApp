package com.xinguang.myapp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by shangwf on 2017/5/27.
 */

public class ResUtils {
    public static String getText(Context context,int resId){
        return context.getResources().getString(resId);
    }

    public static int getColor(Context context,int colorId){
        return context.getResources().getColor(colorId);
    }
    public static Drawable getDrawable(Context context,int drawable){
        return context.getResources().getDrawable(drawable);
    }
}

