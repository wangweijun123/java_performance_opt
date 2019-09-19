package com.example.wangweijun.ch4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.wangweijun.ch4.Master.Master;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        testMasterWorker();
    }


    public void testMasterWorker() {
        Master master = new Master(new Master.CallBack() {
            @Override
            public void onComplete(Object result) {
                System.out.println(result);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        for (int i = 1; i < 5; i++) {
            master.addTask(i);
        }
        master.execute();
    }
}
