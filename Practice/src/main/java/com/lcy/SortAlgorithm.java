package com.lcy;

import java.util.Arrays;
import java.util.Random;

/**
 * @author lichenyu
 */
public class SortAlgorithm {
    private static final int SIZE = 10;

    public static void main(String[] args) {
        int[] array = generateRadomArray();

        printArray(array);
        bubbleSort(array);
        printArray(array);

        check(array);
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            if (minIndex != i) {
                swap(arr, minIndex, i);
            }
        }
    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 插入排序
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    /**
     * 希尔排序
     */
    private static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     */
    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < pivot) l++;
            while (arr[r] > pivot) r--;
            if (l >= r) break;
            swap(arr, l, r);
            if (arr[l] == pivot) r--;
            if (arr[r] == pivot) l++;
        }
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) quickSort(arr, left, r);
        if (right > l) quickSort(arr, l, right);
    }

    /**
     * 归并排序
     */
    private static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 堆排序
     */
    private static void heapSort(int[] arr) {

    }

    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];

    }


    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        int r = mid + 1;
        int t = 0;
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) temp[t++] = arr[l++];
            else temp[t++] = arr[r++];
        }
        while (l <= mid) temp[t++] = arr[l++];
        while (r <= right) temp[t++] = arr[r++];
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) arr[tempLeft++] = temp[t++];
    }

    private static void check(int[] arr) {
        int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        Arrays.sort(arr2);
        boolean same = true;
        for (int i = 0; i < arr2.length; i++) {
            if (arr[i] != arr2[i]) {
                same = false;
                break;
            }
        }
        if (!same) {
            throw new RuntimeException("校验失败");
        }
    }

    private static int[] generateRadomArray() {
        int[] array = new int[SortAlgorithm.SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt() % 100;
        }
        return array;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

}
