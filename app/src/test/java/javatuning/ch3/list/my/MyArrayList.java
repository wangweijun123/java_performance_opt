package javatuning.ch3.list.my;


import org.junit.Test;

import java.util.Arrays;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * add(element), add(index, element), remove(element), remove(index)
 * size()
 *
 */
public class MyArrayList {
    private static final int DEFAULT_CAPACITY = 10;
    // 记录数组中的有效数据
    int size;

    Object[] elementData; // elementData.length 代表容量哈

    MyArrayList() {
        size = 0;
        elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * 添加到尾部 (容量不足，涉及到数组内存copy)
     * @param element
     */
    public void add(Object element) {
        ensureCapacity(size + 1);
        elementData[size ++] = element;
    }

    /**
     * 添加到指定位置, 涉及到扩容, 涉及到数组copy
     * @param index
     * @param element
     */
    public void add(int index, Object element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ensureCapacity(size + 1);

        System.arraycopy(elementData, index, elementData, index+1, size - index);

        elementData[index] = element;
        size ++;
    }

    /**
     * 删除指定位置
     * position: 尾部: 最快，不需要数组的移动
     * position: 不是尾部: 需要数组的移动
     *
     * @param index
     */
    public void remove(int index) {
        out.println("remove index:" +index);
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int numMoved = size-index - 1;
        // 数组copy拷贝
        System.arraycopy(elementData,index+1, elementData, index, numMoved);

        elementData[--size] = null;// 先減少一，定位到最后一个元素，设置为null
    }

    /**
     * 删除数组中某个元素: 先找到这个元素，index， 然后System.arrayCopy(srcPosition, destPostion)
     *
     * @param obj
     */
    public void remove(Object obj) {
        out.println("remove Object:");
        boolean isFound = false;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(obj)) {
                isFound = true;
                int numMoved = size-i - 1;
                // 数组copy拷贝
                System.arraycopy(elementData,i+1, elementData, i, numMoved);
                elementData[--size] = null;// 先減少一，定位到最后一个元素，设置为null
            }
        }

         if (!isFound) {
            out.println("没有找到该元素");
         }
    }

    public int getSize() {
        return size;
    }

    public Object get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return elementData[index];
    }

    private void ensureCapacity(int minCapacity) {
//        out.println("minCapacity = " +minCapacity + ", elementData.length = "+elementData.length);
        if (minCapacity > elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);
//            out.println("newCapacity = " +newCapacity);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

}
