package javatuning.ch4.prodcom.me2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{
    public static final int SLEEPTIME = 1000;
    private volatile boolean isRunning = false;
    // 缓存任务队列
    private final BlockingQueue<MyData> queue;
    static AtomicInteger count = new AtomicInteger(0);

    public Producer(BlockingQueue<MyData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start producer tid="+Thread.currentThread().getId());
        Random random = new Random();
        isRunning = true;
        try {
            while (isRunning) {
                Thread.sleep(random.nextInt(SLEEPTIME));
                MyData data = new MyData(count.getAndIncrement());
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.err.println("failed to put data : " + data);
                }
            }
            System.out.println("stop producer tid="+Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
