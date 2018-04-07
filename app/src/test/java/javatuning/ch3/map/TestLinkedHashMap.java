package javatuning.ch3.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestLinkedHashMap {
	Map<String, String> map;
	
	public void testOutputMap(String funcname){
        map.put("1", "aa");
        map.put("2", "bb");
        map.put("3", "cc");
        map.put("4", "dd");

		map.put("5", "ee");
		map.put("6", "ff");
		map.put("7", "gg");
		map.put("8", "hh");
		map.put("9", "jj");

        map.get("3");
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            System.out.println(name+"->"+map.get(name));
           // System.out.println(name);
        }
	}
	@Test
	public void testLinkedHashMap() {// java.util.ConcurrentModificationException
		map=new LinkedHashMap<String, String>(16,0.75f,true);
		testOutputMap("LinkedHashMap_false");
	}


	@Test
	public void testLinkedHashMap2() {
		map=new LinkedHashMap<String, String>(16,0.75f,true);
		map.put("1", "aa");
		map.put("2", "bb");
		map.put("3", "cc");
		map.put("4", "dd");
		map.put("5", "ee");
		map.put("6", "ff");
		map.put("7", "gg");
		map.put("8", "hh");
		map.put("9", "jj");
		map.get("3");
		Iterator<Map.Entry<String,String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String,String> entry = iter.next();
			if ("7".equals(entry.getKey())) {
				iter.remove();// 集合遍历时，使用迭代器做删除操作
//				map.remove(entry.getKey());// 不能用这种方式删除，抛异常,如果时最后一个ok哈哈
			} else {
				System.out.println(entry.getKey()+"->"+entry.getValue());
			}
		}
	}

	@Test
	public void testLinkedHashMapAccessOrderFalse() {
		map=new LinkedHashMap<String, String>(16,0.75f,false);
		testOutputMap("testLinkedHashMapAccessOrderFalse");
	}

	@Test
	public void testHashMap() {
		map=new HashMap<String,String>();
		testOutputMap("testHashMap");
	}
}
