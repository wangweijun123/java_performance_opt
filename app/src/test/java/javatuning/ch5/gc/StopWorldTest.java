package javatuning.ch5.gc;

import org.junit.Test;

import java.util.HashMap;

/**
 * -Xmx512M -Xms512M -XX:+UseSerialGC -Xloggc:gc.log -XX:+PrintGCDetails
 *  -Xmx512M -Xms512M -XX:+UseParallelGC  -XX:+UseParallelOldGC -XX:ParallelGCThreads=2 -Xloggc:gc.log -XX:+PrintGCDetails
 *  -Xmx512M -Xms512M -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -Xloggc:gc.log -XX:+PrintGCDetails
 * @author Administrator
 *
 */
public class StopWorldTest {
	public static class MyThread extends Thread{
		HashMap map=new HashMap();
		@Override
		public void run(){
			try{
				while(true){
					if(map.size()/100>=1){
						map.clear();
						System.out.println("clean map");
					}
					byte[] b1;
					for(int i=0;i<100;i++){
						b1=new byte[1024];
						map.put(System.nanoTime(), b1);
					}
					Thread.sleep(1);
				}
			}catch(Exception e){
				
			}
		}
	}
	public static class PrintThread extends Thread{
		public static final long starttime=System.currentTimeMillis();
		@Override
		public void run(){
			try{
				while(true){
					long t=System.currentTimeMillis()-starttime;
					System.out.println(t/1000+"."+t%1000);
					Thread.sleep(100);
				}
			}catch(Exception e){
				
			}
		}
	}

	@Test
	public void main(){
		MyThread t=new MyThread();
		PrintThread p=new PrintThread();
		t.start();
		p.start();

		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void main2(){
		Object o = new Object();
	}
}
