package com.example.wangweijun.ch4.ch4;

import android.app.Application;

/**
 * Created by wangweijun on 2018/4/14.
 */

public class PerformanceApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivitiesLifecycleListener());
    }
}
