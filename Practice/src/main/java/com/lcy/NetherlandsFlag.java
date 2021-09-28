package com.lcy;

import static com.lcy.ArrayUtil.*;

/**
 * 荷兰国旗问题
 */
public class NetherlandsFlag {
    public static void main(String[] args) {
        int[] array = getRandomArray();
        int num = getRandomInt();
        System.out.println(num);
        print(array);
        partition(array,0, array.length-1,  num);
        print(array);
    }

    private static void partition(int[] array,int l, int r, int num) {
        int less = l - 1;
        int more = r + 1;
        int index = l;
        while (index < more) {
            if (array[index] < num) {
                swap(array, ++less, index++);
            } else if (array[index] == num) {
                index++;
            } else {
                swap(array, --more, index);
            }
        }
    }
}
