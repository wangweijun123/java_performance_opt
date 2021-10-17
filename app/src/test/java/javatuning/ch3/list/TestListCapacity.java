package javatuning.ch3.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *-Xmx512M -Xms512M
 * @author Administrator
 *
 */
public class TestListCapacity {
	protected List list;
	
	protected void testAddTail(String funcname){
		Object obj=new Object();
		long starttime=System.currentTimeMillis();
		
		for(int i=0;i<1000000;i++){
			list.add(obj);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(funcname+": "+(endtime-starttime));
	}
	
	@Test
	public void testAddTailArrayList() {
		list=new ArrayList();
		testAddTail("testAddTailArrayList");
	}	
	
	@Test
	public void testAddTailArrayListCapacity() {
		list=new ArrayList(1000000);
		testAddTail("testAddTailArrayListCapacity");
	}


	@Test
	public void testArrayListCapacity() {
		list=new ArrayList();
	}
	
}
