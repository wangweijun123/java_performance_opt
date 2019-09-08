package javatuning.ch4.mstrwkr_me3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Master {

    // 任务队列
    List<Worker> tasks = new ArrayList();

    int taskCount = 0;

    // worker 集合, string代表worker id号
    Executor executor = Executors.newFixedThreadPool(3);

    // 结果集合(任务id与结果)
    Map<String, Object> resultMap = new ConcurrentHashMap<>();

    CallBack callBack;

    int result = 0;

    public interface CallBack {
        void onComplete(int result);
    }

    public Master(CallBack callBack) {
        this.callBack = callBack;
    }

    public void addTask(int i) {
        Worker worker = new Worker(this, i, resultMap);
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
