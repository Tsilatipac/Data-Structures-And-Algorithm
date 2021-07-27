package com.lcy;

import java.util.*;

public class Arrays {

    public static int[] generateRandomArray() {
        return generateRandomArray(10);
    }

    public static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(20);
        }
        return arr;
    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void print(int[] arr) {
        System.out.println(java.util.Arrays.toString(arr));
    }
}
