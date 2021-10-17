package javatuning.ch3.map;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * HashMap (手写map，也可以)
 * 列表的数组 (数组大小，扩容问题, 根据hash生成的数组index一致的问题)
 * 初始容量 16， 加载因子 0.75
 * 第一个问题:  什么时候扩容
 *
 * // 在Application onCreate执行的时候调用applicationOnCreate方法
 * @Pointcut("execution(* android.app.Application.onCreate(android.content.Context)) && args(context)")
 * public void applicationOnCreate(Context context) {
 *
 * }
 * // 在调用applicationOnCreate方法之后调用applicationOnCreateAdvice方法
 * @After("applicationOnCreate(context)")
 * public void applicationOnCreateAdvice(Context context) {
 *     AH.applicationOnCreate(context);
 * }
 *
 *
 *
 *
 *
 * About
 * Matrix is a plugin style, non-invasive APM system developed by WeChat.
 *
 * Topics
 * android wechat apm-client
 * Resources
 *  Readme
 * License
 *  View license
 * Releases 11
 * v2.0.0/2021-08-03
 *
 *
 *
 * map.put(0, 0);
 *
 * hash=0, key=0
 * 第一次map放入一个元素，初始化 一个容量16的数组，加载因子0.75， 临界值:12
 *
 * 根据hash值生成数组的索引，放在了第0个位置
 *
 * 放完之后，数组中元素个数size++, 与临界值去判断是否扩容
 *
 * 第二次:
 *
 * hash=1, key=1
 *
 * 当map(数组)元素个数超过临界值12时，开始resize扩容，如何扩。。。
 *
 *
 * 老的容量<<1 ,相当于在老的容量之上乘以2 就是 16*2 = 32， 现在的临界值 32*0.75 = 24；
 *
 * 对象数组 如何copy， 这里是for 循环老数组的容量，
 *
 *
 * 下一次扩容临界24，当加到25个时候扩容，新的容量是老的容量的2倍，左移一位, 64,临界值 48
 *
 * key ---> hash(key.hashCode(),xx)  ---indexforhash(hash)---- 数组的索引,
 *
 * 数组长度-1 & 一个值 一定不会超过  数组长度-1 ， 可能冲突 测试key.hashCode 返回同一个值就可以测试出来
 *
 * 冲突之后，判断hash与key是否可以相等，如果相等覆盖，否则加入到列表的next下一个节点，
 *
 *  HashSet 是对 HashMap的一个封装， 有一个成员HashMap
 */
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
        map = new HashMap();// map的迭代器变量无序，也就是随机的哈
        testGet("testHashMapGet");
    }


    @Test
    public void testHashMapIterator() {
        map = new HashMap();
        for (int i = 0; i < 1000; i++) {
            map.put("" + i, "" + i);
        }
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) { // map的迭代器变量无序，也就是随机的哈
            Map.Entry<String, String> entry = iter.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @Test
    public void testHashMapUseBeanForKey() {
        // map 与 set 当java 对象作为key的时候, 注意唯一性
       /* map = new HashMap();
        for (int i = 0; i < 32; i++) {
            map.put(new Employee("xxxx"+i), "" + i);
        }
        Iterator<Map.Entry<Employee, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Employee, String> entry = iter.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }*/

        Set<Employee> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(new Employee("xxxx"));
        }
        System.out.println("set size:"+set.size());
    }

    @Test
    public void testHashMapKey() {
        map = new HashMap();
        for (int i = 0; i < 32; i++) {
            map.put(i, i);
        }
    }

    /**
     * java bean作为key时的注意事项复写equals与hashcode方法,Map中的key的唯一性与Set的元素唯一性
     */
    static class Employee {
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
//            return name.hashCode();
            return 11; // error spend
        }
    }
}
