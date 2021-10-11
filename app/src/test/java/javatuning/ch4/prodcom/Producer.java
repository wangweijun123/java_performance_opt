package javatuning.ch4.prodcom;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
	private volatile boolean isRunning = true;
	private BlockingQueue<PCData> queue;
	// 静态或者说类变量来记录生成的个数 AtomicInteger
	private static AtomicInteger count = new AtomicInteger();
	private static final int SLEEPTIME = 1000;

	public Producer(BlockingQueue<PCData> queue) {
		this.queue = queue;
	}

	public void run() {
		PCData data = null;
		Random r = new Random();

		System.out.println("start producer tid="+Thread.currentThread().getId());
		try {
			while (isRunning) {
				Thread.sleep(r.nextInt(SLEEPTIME));
				data = new PCData(count.incrementAndGet());
				System.out.println(data+" is put into queue");
				// 在有界队列中，有可能超出maxsize，注意这里哦，不能直接丢弃,现在
				if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
					System.err.println("failed to put data : " + data);
				}
			}
			System.out.println("stop producer tid="+Thread.currentThread().getId());
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	public void stop() {
		isRunning = false;
	}
}