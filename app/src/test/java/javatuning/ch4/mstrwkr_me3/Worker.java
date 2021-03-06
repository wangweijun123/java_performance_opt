package javatuning.ch4.mstrwkr_me3;

import java.util.Map;
import java.util.Random;

public class Worker implements Runnable {
    Map<String, Object> resultMap;
    Master master;
    int i;
    public Worker(Master master, int i, Map<String, Object> resultMap) {
        this.master = master;
        this.i = i;
        this.resultMap = resultMap;
    }


    @Override
    public void run() {
        Object re = handle(i);
        resultMap.put(String.valueOf(i), re);
        master.notityResult();
    }

    public Object handle(int input) {
        try {
            int time = new Random().nextInt(5) * 10;
            Thread.sleep(time);
            System.out.println("tid:" + Thread.currentThread().getName() + "执行任务时间 " + time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return input;
    }
}
