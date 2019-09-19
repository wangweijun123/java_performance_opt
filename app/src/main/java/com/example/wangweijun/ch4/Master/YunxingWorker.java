package com.example.wangweijun.ch4.Master;

import java.util.Map;

public class YunxingWorker extends Worker {
    public YunxingWorker(Master master, int i, Map<String, Object> resultMap) {
        super(master, i, resultMap);
    }

    @Override
    public Object handle(int input) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
