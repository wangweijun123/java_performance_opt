package com.example.wangweijun.ch4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wangweijun.ch4.Master.Master;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        testMasterWorker();
    }


    public void testMasterWorker() {
        Master master = new Master(new Master.CallBack() {
            @Override
            public void onComplete(int result) {
                System.out.println(result);
            }
        });
        for (int i = 1; i < 500; i++) {
            master.addTask(i);
        }
        master.execute();
    }
}
