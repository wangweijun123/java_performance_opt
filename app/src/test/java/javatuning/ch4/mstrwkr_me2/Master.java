package javatuning.ch4.mstrwkr_me2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

    // 任务队列
    Queue<Object> taskQueue = new ConcurrentLinkedQueue<>();

    int taskCount = 0;

    // worker 集合, string代表worker id号
    Map<String, Thread> threadMap = new HashMap<>();

    // 结果集合(任务id与结果)
    Map<String, Object> resultMap = new ConcurrentHashMap<>();

    int workerCount = 10;

    CallBack callBack;

    interface CallBack {
        void onComplete(int result);
    }

    public Master(CallBack callBack) {
        this.callBack = callBack;
        Worker worker = new Worker(this, taskQueue, resultMap);
        for (int i = 0; i < workerCount; i++) {
            threadMap.put(String.valueOf(i), new Thread(worker, String.valueOf(i)));
        }
    }

    public void addTask(Object task) {
        taskQueue.add(task);
        taskCount = taskQueue.size();
    }

    public void execute() {
        Iterator<String> iterator = threadMap.keySet().iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            threadMap.get(next).start();
        }
    }

    public boolean isComplete() {
        Set<Map.Entry<String, Thread>> entries = threadMap.entrySet();
        for (Map.Entry<String, Thread> map : entries) {
            if (map.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    int result = 0;

    public void notityResult() {
        if (resultMap.size() == taskCount) {
            if (resultMap.size() > 0) {
                Iterator<Object> iterator1 = resultMap.values().iterator();
                while (iterator1.hasNext()) {
                    Object next = iterator1.next();
                    result += (int) next;
                    iterator1.remove();
                }
            }
            if (callBack != null) {
                callBack.onComplete(result);
            }
        }
    }
}
