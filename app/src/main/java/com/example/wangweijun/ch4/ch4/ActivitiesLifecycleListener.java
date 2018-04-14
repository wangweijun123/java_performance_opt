package com.example.wangweijun.ch4.ch4;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by wangweijun on 2018/4/14.
 */

public class ActivitiesLifecycleListener implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "Activities";

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated activity:" + activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.i(TAG, "onActivityStarted activity:" + activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.i(TAG, "onActivityResumed activity: " + activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.i(TAG, "onActivityPaused activity: " + activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.i(TAG, "onActivityStopped activity: " + activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.i(TAG, "onActivitySaveInstanceState activity: " + activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.i(TAG, "onActivityDestroyed activity: " + activity);
    }
}
