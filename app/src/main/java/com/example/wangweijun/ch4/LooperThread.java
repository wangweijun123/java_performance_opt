package com.example.wangweijun.ch4;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.annotation.NonNull;

public class LooperThread extends Thread{
    Handler handler;
    Looper looper;
    public LooperThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        super.run();
        // 准备looper
        Looper.prepare();
        synchronized (this) {
            looper = Looper.myLooper();
            notifyAll();
        }
        // 进入死循环
        Looper.loop();
    }

    private Handler getThreadHandler() {
        if (handler == null) {
            handler = new Handler(getLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    //处理即将发送过来的消息
                    System.out.println("thread id="+Thread.currentThread().getId() + ", name:" + Thread.currentThread().getName());
                }
            };
        }
        return handler;
    }

    private Looper getLooper() {
        synchronized (this) {
            while (looper == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return looper;
    }

    public void sendMessage(int what) {
        getThreadHandler().sendEmptyMessage(what);
    }
}
