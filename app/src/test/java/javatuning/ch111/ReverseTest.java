package javatuning.ch111;

import org.junit.Test;

/**
 * Created by wangweijun on 2018/4/12.
 */

public class ReverseTest {

    @Test
    public void testReverse() {
        int[] a = {6, 4, 2, 3, 7, 8};
        print(a);
        int len = a.length;
        int temp;
        for (int i=0; i<len/2; i++) { // 时间复杂度O(n) 空间复杂度O(1)
            temp = a[i];
            a[i] = a[len-1-i];
            a[len-1-i] = temp;
        }
        print(a);
    }

    private void print(int[] a) {
        for (int number : a) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
