package com.lcy;

import java.util.Arrays;

import static com.lcy.ArrayUtil.*;

public class SortAlgorithm {

    public static void main(String[] args) {
        int[] array = getRandomArray();
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        print(array);
        mergeSort(array);
        print(array);
        checkEquals(array, arrayCopy);
    }

    private static void mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        sortProcess(array, 0, array.length - 1);
    }

    private static void sortProcess(int[] array, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        sortProcess(array, L, mid);
        sortProcess(array, mid + 1, R);
        merge(array, L, mid, R);
    }

    private static void merge(int[] array, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }
        while (p1 <= mid) {
            help[i++] = array[p1++];
        }
        while (p2 <= R) {
            help[i++] = array[p2++];
        }
        for (i = 0; i < help.length; i++) {
            array[L + i] = help[i];
        }
    }

    private static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                minIndex = array[j] < array[minIndex] ? j : minIndex;
            }
            swap(array, i, minIndex);
        }
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(array, j, j - 1);
            }
        }
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

}