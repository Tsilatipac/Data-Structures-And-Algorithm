package com.lcy;

/**
 * @author lichenyu
 */
public class ArrayBinaryTreeDemo {
    public static final int LENGTH = 7;

    public static void main(String[] args) {
        int[] arr = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            arr[i] = i + 1;
        }

        new ArrayBinaryTree(arr).preOrder(0);
    }
}

class ArrayBinaryTree {
    private final int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int index) {
        if (null == arr || arr.length == 0 || arr.length < index + 1) {

            return;
        }

        System.out.print(arr[index] + " ");
        preOrder(index * 2 + 1);
        preOrder(index * 2 + 2);
    }
}
