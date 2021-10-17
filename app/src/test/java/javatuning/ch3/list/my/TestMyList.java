package javatuning.ch3.list.my;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class TestMyList {

    @Test
    public void testMyArrayList() {
        MyArrayList myArrayList = new MyArrayList();
        for (int i = 0; i < 18; i++) {
            myArrayList.add(i);
        }

//        int indexInserted = 10;
//        myArrayList.add(indexInserted, 100);
        pringMylist(myArrayList);
//        Integer num = (Integer) myArrayList.get(indexInserted);
//        out.println("indexInserted num = " + num);
//        myArrayList.remove(10);
        myArrayList.remove(new Integer(13));
        pringMylist(myArrayList);
    }

    @Test
    public void testMyLinkedList() {
        MyLinkedList myLinkedList = new MyLinkedList();
        for (int i = 0; i < 18; i++) {
            myLinkedList.add(i);
        }
        myLinkedList.print();
        /*Object target = myLinkedList.get(18);
        out.println("indexInserted target = " + target);*/
        myLinkedList.add(5, 100);
        myLinkedList.print();
    }

    @Test
    public void testMyLinkedListRemoveHead() {
        MyLinkedList myLinkedList = new MyLinkedList();
        for (int i = 0; i < 5; i++) {
            myLinkedList.add(i);
        }
        myLinkedList.print();
        while (myLinkedList.size() > 0) {
            Object o = myLinkedList.removeHead();
            out.println("remove head = " + o + " 之后的列表");
            myLinkedList.print();
        }
//        myLinkedList.removeHead(); // 制造一个异常
    }

    @Test
    public void testMyLinkedListRemoveTail() {
        MyLinkedList myLinkedList = new MyLinkedList();
        for (int i = 0; i < 5; i++) {
            myLinkedList.add(i);
        }
        myLinkedList.print();
        while (myLinkedList.size() > 0) {
            Object o = myLinkedList.removeTail();
            out.println("remove tail = " + o + " 之后的列表");
            myLinkedList.print();
        }
//        myLinkedList.removeTail(); // 制造一个异常
    }

    @Test
    public void testMyLinkedListRemoveMiddle() {
        MyLinkedList myLinkedList = new MyLinkedList();
        for (int i = 0; i < 5; i++) {
            myLinkedList.add(i);
        }
        myLinkedList.print();
        Object o = myLinkedList.removeMiddle(3);
        out.println("remove tail = " + o + " 之后的列表");
        myLinkedList.print();
    }

    private void pringMylist(MyArrayList myArrayList) {
        out.println("#############");
        int size = myArrayList.getSize();
        out.println("size = " + size);
        for (int i = 0; i <size ; i++) {
            out.println(i + " = " + myArrayList.get(i));
        }

    }

    @Test
    public void testArrayList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        list.add(6, 10);
    }

    @Test
    public void testArraysCopy() {

        int[] arr = {1,2, 3,4,5, 0, 0, 0, 0, 0};
        printArr(arr);
        int[] newArr = Arrays.copyOf(arr, 3);

        int indexInsert = 2;
        int dataSize = 5;
        out.println("插入后 ");
        System.arraycopy(arr, indexInsert, arr, indexInsert+1, dataSize - indexInsert);
        arr[indexInsert]  = 10;
        printArr(arr);
//        printArr(arr);
//        printArr(newArr);


    }

    private void printArr(int[] newArr) {
        for (int i : newArr) {
            out.print(i + " ");
        }
        out.println("#############");
    }
}
