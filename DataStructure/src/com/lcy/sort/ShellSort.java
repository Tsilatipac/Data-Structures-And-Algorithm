package com.lcy.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        List<Object> obj = new ArrayList<>();
        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));
        shellSort2(arr);
        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }
    }

    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }
    }

}
