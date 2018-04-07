package javatuning.ch3.map;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapGetPut {
    Map map;
    final int CIRCLE1 = 100000;

    protected void testGet(String funcname) {
        for (int i = 0; i < CIRCLE1; i++) {
            String key = Double.toString(Math.random());
            map.put(key, key);
        }
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE1; i++) {
            String key = Double.toString(Math.random());
            map.get(key);
        }
        long endtime = System.currentTimeMillis();
        System.out.println(funcname + ": " + (endtime - starttime));
    }

    @Test
    public void testHashtableGet() {
        map = new Hashtable();
        testGet("testHashtableGet");
    }

    @Test
    public void testSyncHashMapGet() {
        map = Collections.synchronizedMap(new HashMap());
        testGet("testSyncHashMapGet");
    }

    @Test
    public void testHashMapGet() {
        map = new HashMap();
        testGet("testHashMapGet");
    }


    @Test
    public void testHashMapIterator() {
        map = new HashMap();
        for (int i = 0; i < 1000; i++) {
            map.put("" + i, "" + i);
        }
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @Test
    public void testHashMapUseBeanForKey() {
        map = new HashMap();
        for (int i = 0; i < 10; i++) {
            map.put(new Employee("xxxx"), "" + i);
        }
        Iterator<Map.Entry<Employee, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Employee, String> entry = iter.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        Set<Employee> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(new Employee("xxxx"));
        }
        System.out.println("set size:"+set.size());
    }

    /**
     * java bean作为key时的注意事项复写equals与hashcode方法,Map中的key的唯一性与Set的元素唯一性
     */
    class Employee {
        String name;

        Employee(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Employee) {
                Employee e = (Employee)obj;
                if (e.name.equals(this.name)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }
}
