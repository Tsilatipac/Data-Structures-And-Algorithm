package com.lcy;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtil {
    private static final int N = 10;
    private static final int MAX = 100;
    private static final Random random = new Random();

    public static int getRandomInt() {
        return random.nextInt(MAX);
    }

    public static int[] getRandomArray() {
        int[] array = new int[N];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(MAX);
        }
        return array;
    }

    public static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    public static void checkEquals(int[] array, int[] arrayCopy) {
        Arrays.sort(arrayCopy);
        System.out.println(Arrays.equals(array, arrayCopy));
    }
}
