package com.example.wangweijun.ch4.ch4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.wangweijun.ch4.R;

/**
 * Created by wangweijun on 2018/3/17.
 */

public class MultiThreadActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multi_thread);
    }

    public void stopTheWorld(View v) {
        StopWorldTest.main();
    }
}
