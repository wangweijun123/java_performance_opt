package javatuning.ch4.prodcom.me2;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Customer implements Runnable{

    // 缓存任务队列
    private final BlockingQueue<MyData> queue;

    public Customer(BlockingQueue<MyData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start Customer id="+Thread.currentThread().getId());
        MyData data = null;
        Random r = new Random();
        try {
            Thread.sleep(r.nextInt(Producer.SLEEPTIME));
            while (true) {
                data = queue.take();
                if (data != null) {
                    int re = data.getData() * data.getData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}",
                            data.getData(), data.getData(), re));
                    Thread.sleep(r.nextInt(Producer.SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
