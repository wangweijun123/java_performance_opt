package javatuning.ch3.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Vector
 * ArrayList 数组实现，特点：查找块, 添加(首：数组的copy，越靠前性能越低 中间： last)，
 * 所以项目开发中，如果知道列表的大小，直接指定大小，不会存在扩容的操作 >> 1相当于除以2 然后加上原来的大小
 * 也就是1.5倍
 *
 * LinkedList 双向列表实现，不需要控制容量 读写:  查找(index前半段与后半段), 增删
 *
 * iter.remove (Array --> 删除操作)   Link列表的删除操作
 */
public class TestList {
	private static final int CIRCLE1 = 100000;
	protected List list;
	
	protected void testAddTail(String funcname){
		Object obj=new Object();
		long starttime=System.currentTimeMillis();
		
		for(int i=0;i<500000;i++){
			list.add(obj);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	protected void testDelTail(String funcname){
		Object obj=new Object();
		for(int i=0;i<CIRCLE1;i++){
			list.add(obj);
		}
		
		long starttime=System.currentTimeMillis();
		while(list.size()>0){
			list.remove(list.size()-1);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	protected void testDelFirst(String funcname){
		Object obj=new Object();
		for(int i=0;i<CIRCLE1;i++){
			list.add(obj);
		}
		
		long starttime=System.currentTimeMillis();
		while(list.size()>0){
			list.remove(0);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	protected void testDelMiddle(String funcname){
		Object obj=new Object();
		for(int i=0;i<CIRCLE1;i++){
			list.add(obj);
		}
		
		long starttime=System.currentTimeMillis();
		while(list.size()>0){
			list.remove(list.size()>>1);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	protected void testAddFirst(String funcname){
		Object obj=new Object();
		long starttime=System.currentTimeMillis();
		
		for(int i=0;i<50000;i++){
			list.add(0, obj);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	
	//====add tail vm:  -Xmx512M -Xms512M
	@Test
	public void testAddTailArrayList() {
		list=new ArrayList();
		testAddTail("testAddTailArrayList");// 6 ms
	}
	
	@Test
	public void testAddTailVector() {
		list=new Vector();
		testAddTail("testAddTailVector");
	}
	
	@Test
	public void testAddTailLinkedList() {// 8ms
		list=new LinkedList();
		testAddTail("testAddTailLinkedList");
	}
	
	//====add first
	@Test
	public void testAddFirstArrayList() {
		list=new ArrayList();
		testAddFirst("testAddFirstArrayList");// 216 ms
	}
	
	@Test
	public void testAddFirstVector() {
		list=new Vector();
		testAddFirst("testAddFirstVector");
	}
	
	@Test
	public void testAddFirstLinkedList() {
		list=new LinkedList();
		testAddFirst("testAddFirstLinkedList"); // 3ms
	}
	
	//====delete tail
	@Test
	public void testDeleteTailArrayList() {
		list=new ArrayList();
		
		testDelTail("testDeleteTailArrayList"); // 1 ms
	}
	
	//@Test
	public void testDeleteTailVector() {
		list=new Vector();
		testDelTail("testDeleteTailVector");
	}
	
	@Test
	public void testDeleteTailLinkedList() {
		list=new LinkedList();
		testDelTail("testDeleteTailLinkedList"); // 2ms
	}
	
	//====delete first
	@Test
	public void testDeleteFirstArrayList() {
		list=new ArrayList();
		testDelFirst("testDeleteFirstArrayList"); // 797
	}
	
	@Test
	public void testDeleteFirstVector() {
		list=new Vector();
		testDelFirst("testDeleteFirstVector");
	}
	
	@Test
	public void testDeleteFirstLinkedList() {
		list=new LinkedList();
		testDelFirst("testDeleteFirstLinkedList");// 2 ms
	}
	
	@Test
	public void testDeleteMiddleLinkedList() {
		list=new LinkedList();
		testDelMiddle("testDeleteMiddleLinkedList"); // 3763
	}
	
	@Test
	public void testDeleteMiddleArrayList() {
		list=new ArrayList();
		testDelMiddle("testDeleteMiddleArrayList");// 407
	}
}
