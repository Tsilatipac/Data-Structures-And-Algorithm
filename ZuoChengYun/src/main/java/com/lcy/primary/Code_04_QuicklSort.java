package com.lcy.primary;

import com.lcy.Arrays;

public class Code_04_QuicklSort {
    public static void main(String[] args) {
        int[] arr = Arrays.generateRandomArray(10);
        Arrays.print(arr);
        quickSort(arr, 0, arr.length-1);
        Arrays.print(arr);
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            Arrays.swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                Arrays.swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                Arrays.swap(arr, --more, l);
            } else {
                l++;
            }
        }
        Arrays.swap(arr, more, r);
        return new int[] { less + 1, more };
    }
}
