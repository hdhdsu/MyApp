package com.xinguang.myapp.manager;

import android.app.Activity;
import com.xinguang.myapp.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;
/**
 * Activity管理类
 */
public class AppActivityManager {
    /**
     * 保存Activity的栈
     */
    private static List<BaseActivity> activityStack;

    private AppActivityManager() {
    }

    public static void addActivity(BaseActivity activity) {
        if (activityStack == null) {
            activityStack = new ArrayList<>();
        }
        activityStack.add(activity);
    }

    public static void removeActivity(BaseActivity activity) {

        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public static void finishAll() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public static Activity currActivity() {
        return activityStack.get(activityStack.size()-1);
    }

    public static void finishActivitiesExcept(Class cls) {
        int size=activityStack.size();
        for (int i=size-1;i>0;i--){
            if (cls==activityStack.get(i).getClass()){
                return ;
            }else {
                Activity activity=activityStack.get(i);
                activity.finish();
            }

        }
    }
    public static Activity getActivity(Class cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass() == cls) {
                return activity;
            }
        }
        return null;
    }

    public static List<BaseActivity> getActivityStack() {
        return activityStack;
    }
}
