package com.example.wangweijun.ch4.Master;

import java.util.Map;

public class YunxingWorker extends Worker {
    public YunxingWorker(Master master, int i, Map<String, Object> resultMap) {
        super(master, i, resultMap);
    }

    @Override
    public Object handle(int input) {
        return true;
    }
}
