java 核心数据结构 List , Map,  Set(提示: List与set是Collection的子类, Map 是单独的接口)

1 list 接口实现类

第一 ： ArrayList(数组实现Object[],默认初始化容量10，以1.5的速度增长)
Vector(数组实现Object[], 与ArrayList区别是方法加了synchronized同步,多线程支持)

LinkedList(双向循环链表的实现) {

Node first;
Node last;
    Node {
         Object data;
         Node pre;// 上一个节点
         Node next;// 下一个节点
    }
}

第二 ： 增加元素到列表的尾部比较add(element)
ArrayList 在不牵涉到扩容的时候速度快，如果需要扩容的话，进行数组的copy，效率底下
LinkedList{Node{pre(前驱节点),next(后驱节点)}}双向列表 速度快，没什么好说的,因为有last的引用

增加元素到列表任意位置add(index, element)
ArrayList 数组的拷贝，越靠前性能越差
LinkedList 先找到那个节点,根据节点所在位置，前半段还是后半段,如果在前半段就是从前
           往后找，因为有first节点，如果在后半短，就是从后往前找，因为有last节点

remove 操作与add等价

第三 : 遍历列表(三种方式)

foreach-->编译后其实就是迭代器

迭代器

for循坏，随机访问: ArrayList 比 LinkedList强多了,所以我们从后台取到
                的列表数据显示，都是用ArrayList

第四 ：并发场景使用数据结构
        Collections.synchronizedList(new ArrayList());产生并发安全的list，
        其实就是在Arraylist对应操作时外部加了一层synchronized,用Collections.synchronized(xxx)
        都是一样的思想

        CopyOnWriteArrayList(读多写少的环境，读不加锁) 与 Vector
          CopyOnWriteArrayList: 写的时候也就是add or remove操作
           写copy一份修改完后设置


2 Map 接口实现类

第一 ： HashMap(无序): 对key进行hash,hash值就对应着内存地址，默认初始容量16，loadfactor 负载因子

基于数组的单向列表实现
HashMap {
    Node<K, V>[]

    Node {
       int hash;
       K key;
       V value;
       Node next;(这个就是为hash(key)冲突解决问题的)
    }
}
native 方法比普通方法快,因为操作的是本地链接库的api

第二 ： HashTable 对多线程支持，实现与Hashmap差不多，和Arraylist与vector一样

第三 ： LinkedHashMap(有序的,因为这个Entry继承HashMap.Entry，增加了after,与before引用)
    两种顺序{
        时间顺序 : 元素访问顺序，倒序
        插入顺序 : 先插入，先出来
        }
(List,Map)集合在遍历时，不要对集合做结构性修改(add与remove操作，这里用iter.remove)

第四 ： TreeMap (implement SortedMap, 有序的map, 其实就是key可以比较，实现comparable接口)
还有一些好用接口

TreeMap NB,把元素放进去就排好序了，根据key来排序，所以遍历的时候是排好序的，而且有nb接口获取
子集合，都不用自己写算法

第四  并发
     ConcurrentHashMap 与 Collections.synchronized(map) 的比较

     ConcurrentHashMap 读不锁,只有写锁,而且锁的粒度比hashMap小


3 Set(元素的唯一性,其实HashMap中key的唯一性的实现) 接口实现类

HashSet (封装了HashMap,也就是底层是HashMap实现)
HashSet {
    HashMap<E,Object> map;// 有这样一个属性,value是一个确定的值
}

LinkedHashSet (封装了LinkedHashMap), 插入顺序，遍历出来

TreeSet (封装了TreeMap)


性能优化，数据结构的选择大方向





4 Queue(单向列表,只有next) 容量，也就是最大值，超过这个值异常，或者callback策略

BlockingQueue 简写线程之间的通信

ConcurrentLinkedQueue  无锁实现，比LinkedBlockingQueue 性能好

Deque(双向列表,因为Node{pre与next节点引用},由节点决定)

LinkedDeque, ArrayDeque, 线程不安全实现类

ConcurrentLinkedDeque 支持多线程