package com.lcy.primary;

import com.lcy.Arrays;

/**
 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的 左边，等于num的数放在数组的中间，大于num的数放在数组的 右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 */
public class Code_08_NetherlandsFlag {
    public static void main(String[] args) {
        int[] arr = Arrays.generateRandomArray(10);
        Arrays.print(arr);
        test(arr, 0, arr.length - 1, 10);
        Arrays.print(arr);
    }

    private static void test(int[] arr, int L, int R, int num) {
        int less = L - 1;
        int more = R + 1;
        int C = L;
        while (C < more) {
            if (arr[C] < num) {
                Arrays.swap(arr, ++less, C++);
            } else if (arr[C] > num) {
                Arrays.swap(arr, --more, C);
            } else {
                C++;
            }
        }
    }


}
