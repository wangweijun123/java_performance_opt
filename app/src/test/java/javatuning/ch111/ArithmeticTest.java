package javatuning.ch111;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wangweijun on 2018/4/9.
 */

public class ArithmeticTest {

    /**
     * 找到两个数之和最接近于0的两个数
     */
    @Test
    public void test() {
        int[] arr = {-17, 38, 8, 6, 20, -34, -24};
        int len = arr.length;
        if (len < 2) {
            return;
        }
        int minResult = arr[0] + arr[1];
        int leftIndex = 0;
        int rightIndex = 1;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int newResult = Math.abs(arr[i] + arr[j]);// (n-1)+(n-2) +.... 0 == n*(n-1)/2 --->n^2两个数的和最接近于0,理解绝对值最小
                if (newResult < minResult) {
                    leftIndex = i;
                    rightIndex = j;
                    minResult = newResult;
                }
            }
        }
        System.out.print("leftIndex:" + leftIndex + ", rightIndex:" + rightIndex + ", minResult:" + minResult);
        // leftIndex:0, rightIndex:4, minResult:3
    }

    /**
     * 找到两个数之和最接近于0的两个数
     */
    @Test
    public void test2() {
        int[] arr = {-17, 38, 8, 6, 20, -34, -24};
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("冒泡排序完毕");// -34 -24 -17 6 8 20 38 冒泡排序完毕

        int minLeft = 0;
        int minRight = len - 1;
        int minAbs = Integer.MAX_VALUE;

        int l = minLeft;
        int r = minRight;
        while (l < r) {
            int sum = arr[l] + arr[r];
            int abs = Math.abs(sum);
            if (abs < minAbs) {
                minAbs = abs;
                minLeft = l;
                minRight = r;
            }
            if (sum > 0) {
                r--;
            } else {
                l++;
            }
        }

//        -34 -24 -17 6 8 20 38 冒泡排序完毕
//        minLeft:2, minRight:5, minAbs:3
        System.out.println("minLeft:" + minLeft + ", minRight:" + minRight + ", minAbs:" + minAbs);
    }


    /**
     * 排序数组中只有0与1，以及时间复杂度
     */
    @Test
    public void test3() {
        int[] arr = {0, 1, 0, 0, 1, 0, 1};
        int len = arr.length;
        int zeroCount = 0;// 0的个数
        for (int i = 0; i < len; i++) {
            if (arr[i] == 0) {// O(n)
                zeroCount++;
            }
        }
        for (int i = 0; i < len; i++) {
            arr[i] = (i < zeroCount) ? 0 : 1; // O(n)
        }

        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    @Test
    public void test4() {
        int[] arr = {0, 1, 0, 0, 1, 0, 1, 2, 2, 0};
        int len = arr.length;
        int zeroCount = 0;
        int oneCount = 0;
        for (int i = 0; i < len; i++) {
            switch (arr[i]) {
                case 0:
                    zeroCount++;
                    break;
                case 1:
                    oneCount++;
                    break;
                case 2:// do nothing
                    break;
            }
        }
        for (int i = 0; i < len; i++) {
            if (i < zeroCount) {
                arr[i] = 0;
            } else if (i < (zeroCount + oneCount)) {
                arr[i] = 1;
            } else {
                arr[i] = 2;
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    /**
     * 数组中各元素出现的次数
     */
    @Test
    public void test5() {
        int[] arr = {0, 1, 0, 0, 1, 0, 1, 2, 2, 0, 2};
        int len = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            if (map.containsKey(arr[i])) {// O(n)
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = iter.next();
            System.out.println(entry.getKey() + " 次数:" + entry.getValue());
        }
    }

    /**
     * 寻找在数组中过半的元素
     */
    @Test
    public void test6() {
        int[] arr = {0, 1, 2, 2, 1, 2, 1, 2, 2, 0, 2};
        int len = arr.length;
        boolean exist = false;
        for (int i=0; i<len; i++) {
            int temp = arr[i];
            int tempCount = 0;
            for (int j=0; j<len; j++) {
                if (temp == arr[j]) {
                    tempCount ++;
                }
            }
            if (tempCount > len/2) {
                exist = true;
                System.out.println("找到 "+ temp);
                break;
            }

        }
        if (!exist) {
            System.out.println("没找到");
        }
    }


    @Test
    public void test7() {
        // 1*1*1 + 2*2*2 + ..... + 10*10*10 + n * n * n
        System.out.println(lifang(3));
    }

    public int lifang(int n) {
        int result = 0;
        for (int i=1; i<=n; i++) {
            result = result + i * i * i;
        }
        return result;
    }
}
