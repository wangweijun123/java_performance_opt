package javatuning.ch3.ref;

import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * JVM根据当前堆的使用情况，当接近最大值时，才会自动回收软引用包裹的对象-->适合做缓存
 *
 *
 */
public class TestSoftRef {

	ReferenceQueue<MyObject> softQueue=null;

	public class CheckRefQueue extends Thread{

		@SuppressWarnings("unchecked")
		@Override
		public void run(){
			while(true){
				if(softQueue!=null){
					Reference<MyObject> obj=null;
					try {
						obj = (Reference<MyObject>) softQueue.remove();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(obj!=null)
						System.out.println("Object for SoftReference is "+obj.get());

				}
			}
		}
	}
//	public class MemConsumer extends Thread{
//		HashMap map=new HashMap();
//		@Override
//		public void run(){
//			for(int i=0;i<10000;i++){
//				map.put(i, new StringBuffer().append(i));
//			}
//		}
//	}

	@Test
	public void test() throws InterruptedException {
		// 普通对象，软引用对象，只是这个软引用对象包裹了这个普通对象
		// 1 去除强引用， 2 只有这个软的包裹他了，
		// 3 分配一个大内存，接近测试的分配堆内存，破死他去gc，如果哪个普通对象被回收了
		// 会进入引用队列 ，调用finizal函数，会背认为回收了
		MyObject obj=new MyObject();
		softQueue = new ReferenceQueue<MyObject>();
		SoftReference<MyObject> softRef=new SoftReference<MyObject>(obj,softQueue);

		new CheckRefQueue().start();
		//去除强引用
		obj=null;
		System.gc();
		System.out.println("After GC:Soft Get= " + softRef.get());
		System.out.println("分配大块内存");
		byte[] b=new byte[4*1024*925];
		System.out.println("After new byte[]:Soft Get= " + softRef.get());
	}

	@Test
	public void testSimple() throws InterruptedException {
		MyObject obj=new MyObject();

		SoftReference<MyObject> softRef=new SoftReference<MyObject>(obj);
		//去除强引用
		obj=null;
		System.out.println("Soft Get: " + softRef.get());
		System.gc();
		System.out.println("Soft Get: " + softRef.get());
		byte[] b=new byte[4*1024*925];
		System.out.println("Soft Get: " + softRef.get());
	}
}
