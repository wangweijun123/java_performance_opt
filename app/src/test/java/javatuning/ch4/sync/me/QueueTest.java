package javatuning.ch4.sync.me;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangweijun on 2018/4/7.
 */

public class QueueTest {

    @Test
    public void test() {
        final BlockQueue blockQueue = new BlockQueue();
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    blockQueue.take();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    blockQueue.put(atomicInteger.getAndIncrement());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class BlockQueue {

        List<Integer> list = new ArrayList<>();

        int max = 10;

        public Integer take() {
            synchronized (this) {
                while (list.size() == 0) {
                    try {
                        System.out.println(Thread.currentThread().getId() +" 消费线程等待。。。size:"+list.size());
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer integer = null;
                if (list.size() > 0) {
                    integer = list.remove(0);
                    System.out.println(Thread.currentThread().getId() +" 消费线程消费 " +integer+" size:"+list.size());
                }
                this.notify();
                return integer;
            }
        }

        public void put(Integer number) {
            synchronized (this) {
                while (list.size() == max) {
                    try {
                        System.out.println(Thread.currentThread().getId() +" 生产线程等待。。。size:"+list.size());
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (list.size() < max) {
                    list.add(number);
                    System.out.println(Thread.currentThread().getId() +" 生产线程 add number:"+number+", size:"+list.size());
                }
                this.notify();
            }
        }
    }
}
