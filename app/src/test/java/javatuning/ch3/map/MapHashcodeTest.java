package javatuning.ch3.map;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapHashcodeTest {
	public static class GoodHash{
		double d;
		public GoodHash(double d){
			this.d=d;
		}
	}
	public static class BadHash{
		double d;
		public BadHash(double d){
			this.d=d;
		}
		@Override
		public int hashCode(){
			return 1;
		}
	}
	
	Map map;
	final int CIRCLE1=10000;
	
	@Test
	public void testGetGoodHash(){
		map=new HashMap();
		for(int i=0;i<CIRCLE1;i++){
			GoodHash key=new GoodHash(Math.random());
			map.put(key,key);// 取出key，进行hash算法后比较,如果以对象作为key的化
		}
		long starttime=System.currentTimeMillis();
		for(int i=0;i<CIRCLE1;i++){
			GoodHash key=new GoodHash(Math.random());
			map.get(key);
		}
		long endtime=System.currentTimeMillis();
		System.out.println("testGetGoodHash"+": "+(endtime-starttime));
	}
	
	
	@Test
	public void testGetBadHash(){
		map=new HashMap();
		for(int i=0;i<CIRCLE1;i++){
			BadHash key=new BadHash(Math.random());
			map.put(key,key);
		}
		long starttime=System.currentTimeMillis();
		for(int i=0;i<CIRCLE1;i++){
			BadHash key=new BadHash(Math.random());
			map.get(key);
		}
		long endtime=System.currentTimeMillis();
		System.out.println("testGetBadHash"+": "+(endtime-starttime) + ", size:"+map.size());
	}


	@Test
	public void testPerson(){
		HashMap map=new HashMap();
		for(int i=0;i<3;i++){
			// 只要名字相同，就认为是同一个人,所以在Person中复写hashcode与equals方法，根据字符串才来判断
			Person key=new Person("xxx");
			map.put(key,key);
		}
		System.out.println("size:"+map.size());
	}

	public static class Person{
		String name;
		public Person(String name){
			this.name=name;
		}
		@Override
		public int hashCode(){
			return name.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return name.equals(((Person)obj).name);
		}
	}
}
