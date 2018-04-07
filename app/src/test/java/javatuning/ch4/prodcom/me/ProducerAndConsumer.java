package javatuning.ch4.prodcom.me;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangweijun on 2018/4/6.
 */

public class ProducerAndConsumer {

    @Test
    public void main() throws Exception {
        BlockingQueue<Wotou> queue = new LinkedBlockingQueue<>(10);
        AtomicInteger atomicInteger = new AtomicInteger(1);
        Prod p1 = new Prod(queue, atomicInteger);
        Cusumer c1 = new Cusumer(queue);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(p1);
        Thread.sleep(2000);
        System.out.println("我睡了两秒");
        service.execute(c1);

        Thread.sleep(10000);
    }

    class Prod implements Runnable {
        BlockingQueue<Wotou> queue;
        AtomicInteger atomicInteger;

        Prod(BlockingQueue<Wotou> queue, AtomicInteger atomicInteger) {
            this.queue = queue;
            this.atomicInteger = atomicInteger;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int index = atomicInteger.getAndIncrement();
                    Thread.sleep(100);
                    queue.put(new Wotou(index));// 阻塞，queue是有容量的
                    System.out.println("tid:" + Thread.currentThread().getId() + " 放入队列 index:" + index + ", queue size:" + queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Cusumer implements Runnable {
        BlockingQueue<Wotou> queue;

        Cusumer(BlockingQueue<Wotou> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(10);
                    Wotou wotou = queue.take();// 阻塞，queue为空了
                    System.out.println("tid:" + Thread.currentThread().getId() + "消费完 index:" + wotou.i + ", queue size:" + queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Wotou {
        int i;

        Wotou(int i) {
            this.i = i;
        }
    }

}
