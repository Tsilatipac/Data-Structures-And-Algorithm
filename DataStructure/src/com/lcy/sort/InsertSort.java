package com.lcy.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        int insertValue;
        int insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertValue;
        }
    }
}
