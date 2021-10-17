package javatuning.ch3.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestLinkedList {

	@Test
	public void test() {
		List<Integer> l=new LinkedList();
//		List<Integer> l=new ArrayList();
		for (int i = 0; i < 18; i++) {
			l.add(i);
		}
		System.out.println(l);
	}

}
