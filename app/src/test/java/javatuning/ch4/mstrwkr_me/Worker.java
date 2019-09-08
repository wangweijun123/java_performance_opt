package javatuning.ch4.mstrwkr_me;

import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class Worker implements Runnable{

    Queue<Object> taskQueue;
    Map<String, Object> resultMap;

    public Worker(Queue<Object> taskQueue, Map<String, Object> resultMap) {
        this.taskQueue = taskQueue;
        this.resultMap = resultMap;
    }
    @Override
    public void run() {
        while (true) {
            Object task = taskQueue.poll();
            if (task == null) {
                System.out.println("没任务了 tid:"+Thread.currentThread().getName() + "退出");
                break;
            }
            System.out.println("取到任务 tid:"+Thread.currentThread().getName() + ", task name :" + task.toString());
            Object re = handle(task);
            resultMap.put(task.toString(), re);
        }
    }

    public Object handle(Object task) {
        try {
            int i = new Random().nextInt(5) * 1000;
            Thread.sleep(i);
            System.out.println("tid:"+Thread.currentThread().getName() + "执行任务时间 "+i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int input = (int) task;
       return input * input;
    }
}
