package com.example.wangweijun.ch4;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Log.i("wang", "what :"+msg.what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void sendMsg(View v){
        handler.sendEmptyMessage(1);
    }
}
