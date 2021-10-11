package javatuning.ch4.prodcom.me2;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    @Test
    public void main() throws Exception{
        BlockingQueue<MyData> blockingQueue = new LinkedBlockingQueue<>(10);

        ExecutorService executorService = Executors.newCachedThreadPool();
        Producer p1 = new Producer(blockingQueue);
        Producer p2 = new Producer(blockingQueue);
        Producer p3 = new Producer(blockingQueue);
        executorService.execute(p1);
        executorService.execute(p2);
        executorService.execute(p3);


        Customer c1 = new Customer(blockingQueue);
        Customer c2 = new Customer(blockingQueue);
        Customer c3 = new Customer(blockingQueue);

        executorService.execute(c1);
        executorService.execute(c2);
        executorService.execute(c3);

        Thread.sleep(10 * 1000);

        p1.stop();
        p2.stop();
        p3.stop();

        Thread.sleep(3_1000);

        executorService.shutdown();
    }
}
