package javatuning.ch4.reentrantLock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockingTest {

	final Lock lock = new ReentrantLock();

	@Test
	public void test() throws InterruptedException {
		Thread first=new Thread(createTask(), "FirstThread");
		Thread second= new Thread(createTask(), "SecondThread");
		first.start();
		second.start();
		Thread.sleep(600);
		second.interrupt();
		Thread.sleep(10000);
	}

	private Runnable createTask() {
		return new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						if (lock.tryLock(500, TimeUnit.MILLISECONDS)) // ReentrantLock.tryLock(500)传一个等待时间
//						if (lock.tryLock())
						//lock.lock();
//						lock.lockInterruptibly();
						{
							try {
								System.out.println("locked "+Thread.currentThread().getName());
								Thread.sleep(1000);
							} finally {
								lock.unlock();
								System.out.println("unlocked  "+ Thread.currentThread().getName());
							}
							break;
						}
						else {
							System.out.println("unable to lock "+ Thread.currentThread().getName());
						}
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getName()+" is Interrupted" );
					}
				} 
			}
		};
	}
}
