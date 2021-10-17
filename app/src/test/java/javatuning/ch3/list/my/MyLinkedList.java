package javatuning.ch3.list.my;

import org.w3c.dom.Node;

import java.util.NoSuchElementException;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * 双向列表
 */
public class MyLinkedList {
    Node first;
    Node last;
    int size;

    MyLinkedList() {

    }

    public void add(int data) {
        // 第一步: 保存好 last
        Node l = last;
        // 第二步：构建newnode, 新节点的pre指针指向l
        Node newNode = new Node(data, l, null);
        // 第三步：移动last指针
        last = newNode;

        // 第四步
        if (l == null) { // 如果原来的list为null, 将first节点指向新节点
            first = newNode;
        } else { // 处理保存的l的next指针,
            l.next = newNode;
        }
        // 第五步:
        size ++;
    }

    public void add(int index, int data) {
        Node targetNode = first;
        while (index > 0) { // 这里优化，从前还是从后
            targetNode = targetNode.next;
            index --;
        }
        // 原来的pre Node
        Node preNode = targetNode.pre;
        // 把新节点的pre, next 指定好
        Node newNode = new Node(data, preNode, targetNode);
        // 打断原来的指针
        preNode.next = newNode;
        targetNode.pre = newNode;

        // 第五步:
        size ++;
    }

    public Object removeMiddle(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeHead();
        }

        if (index == size -1) {
            return removeTail();
        }

        Node targetNode = first;
        while (index > 0) { // 这里优化，从前还是从后
            targetNode = targetNode.next;
            index --;
        }
        Node pre = targetNode.pre;
        Node next = targetNode.next;
        targetNode.pre = null;
        targetNode.next = null;
        pre.next = next;
        next.pre = pre;
        size--;
        return targetNode.item;
    }

    /**
     * 1 先保存first指针
     * 2 然后移动first指针
     * 3 打断该打断的
     *
     * @return
     */
    public Object removeHead() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        Node f = first;
        first = f.next;
        f.next = null;
        if (first == null) {
            last = null;
        } else {
            first.pre = null;
        }

        size --;
        return f.item;
    }

    public Object removeTail() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        Node l = last;
        last = l.pre;
        l.pre = null;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size --;
        return l.item;
    }

    public int size() {
        return size;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node node = first;
        while (index > 0) { // 这里优化，从前还是从后
            node = node.next;
            index --;
        }
        return node.item;
    }

    public void print() {
        out.println(" --------------- size = " + size);
        if (first == null) {
            return;
        }
        Node node = first;
        int index = 0;
        int num = size;
        while (num > 0) {
            out.println(index + " " + node.item);
            node = node.next;
            num --;
            index++;
        }
    }


    private static class Node {
        Object item;
        Node pre;
        Node next;

        public Node(Object item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

}
