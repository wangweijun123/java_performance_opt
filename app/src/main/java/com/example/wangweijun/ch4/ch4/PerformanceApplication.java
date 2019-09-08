package com.example.wangweijun.ch4.ch4;

import android.app.Application;

/**
 * Created by wangweijun on 2018/4/14.
 */

public class PerformanceApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        registerActivityLifecycleCallbacks(new ActivitiesLifecycleListener());
    }
}
