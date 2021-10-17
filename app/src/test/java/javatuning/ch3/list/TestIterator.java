package javatuning.ch3.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TestIterator {
	List<String> list = null;
	private static final int CIRCLE = 1000000;

	public void initList(List<String> list) {
		list.clear();
		for (int i = 0; i < CIRCLE; i++) {
			list.add(String.valueOf(0));
		}
	}

	public void internalTest() {
		String tmp;
		long start = System.currentTimeMillis();
		for (String s : list) { // foreach 循环访问
			tmp = s;
		}
		System.out.println("foreach spend:"
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			// 迭代器访问,迭代器中保留了next元素指针，next(), 直接可以return value，指针又移动到了下一个元素
			tmp = it.next();
		}
		System.out.println("Iterator spend;"
				+ (System.currentTimeMillis() - start));

//		if(list instanceof LinkedList)return;
		start = System.currentTimeMillis();
		int size = list.size();
		for (int i = 0; i < size; i++) { // for 循环随机访问,千万别使用普通的for循环来遍历LinkedList哈
			tmp = list.get(i);
		}
		System.out.println("for spend;" + (System.currentTimeMillis() - start));
	}

	/**
	 * foreach spend:8
	 * Iterator spend;7
	 * for spend;6
	 */
	@Test
	public void testArrayList() {
		list = new ArrayList<String>();
		initList(list);
		internalTest();
	}

	/**
	 * foreach spend:14
	 * Iterator spend;12
	 * for   spend 无穷大
	 */
	@Test
	public void testLinkedList() {
		list = new LinkedList<String>();
		initList(list);
		internalTest();
	}

	@Test
	public void testIterRemove() {
//		list = new LinkedList<String>();
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			list.add(i);
		}
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			iterator.remove();
		}
	}
}
