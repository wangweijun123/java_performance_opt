package javatuning.ch111;

import org.junit.Test;

/**
 * Created by wangweijun on 2018/4/11.
 */

public class BinarySearch {

    /**
     * 二分搜索k
     * 循坏x次找到
     * 时间复杂度 : n/(2^x) = 1
     * 空间复杂度:  O(1)，因为没有使用额外的辅助变量，所以是常量
     */
    @Test
    public void binarySearchTest() {
        int key = 100;
        int[] arr = {-20, -17, 2, 5, 8, 17, 20, 34, 50};
        int left = 0, mid = 0, right = arr.length-1;
        boolean exist = false;
        while (left <= right) {
            mid = (left + right) /2;
            if (key == arr[mid]) {
                exist = true;
                System.out.println("找到了 index:"+mid);
                return ;
            } else if (key < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (!exist) {
            System.out.println("没有");
        }
    }
}
