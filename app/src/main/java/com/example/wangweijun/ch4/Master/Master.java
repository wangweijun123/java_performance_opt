package com.example.wangweijun.ch4.Master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Master {

    // 任务队列
    List<Worker> tasks = new ArrayList();

    int taskCount = 0;

    // worker 集合, string代表worker id号
    Executor executor = Executors.newFixedThreadPool(5);

    // 结果集合(任务id与结果)
    Map<String, Object> resultMap = new ConcurrentHashMap<>();

    CallBack callBack;

    public interface CallBack {
        void onComplete(Object result);
    }

    public Master(CallBack callBack) {
        this.callBack = callBack;
    }

    public void addTask(int i) {
        Worker worker = new YunxingWorker(this, i, resultMap);
        tasks.add(worker);
        taskCount = tasks.size();
    }

    public void execute() {
        for (Worker worker : tasks) {
            executor.execute(worker);
        }
    }

    public void notityResult() {
        if (resultMap.size() == taskCount) {
            if (callBack != null) {
                callBack.onComplete(resultMap);
            }
        }
    }
}
