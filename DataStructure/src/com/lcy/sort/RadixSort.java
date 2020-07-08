package com.lcy.sort;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[10];
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        List<Object> obj = new ArrayList<>();
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCount = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int value : arr) {
                int digitOfElement = value / n % 10;
                bucket[digitOfElement][bucketElementCount[digitOfElement]++] = value;
            }
            int index = 0;
            for (int j = 0; j < bucketElementCount.length; j++) {
                if (bucketElementCount[j] != 0) {
                    for (int k = 0; k < bucketElementCount[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
                bucketElementCount[j] = 0;
            }
        }
    }
}
