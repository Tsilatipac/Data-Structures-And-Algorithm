package com.lcy.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
        System.out.println();
        arrayBinaryTree.infixOrder();
        System.out.println();
        arrayBinaryTree.postOrder();
    }
}

class ArrayBinaryTree {
    private final int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    //前序遍历
    public void preOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        System.out.print(array[index]);
        if ((2 * index + 1) < array.length) {
            preOrder((2 * index + 1));
        }
        if ((2 * index + 2) < array.length) {
            preOrder((2 * index + 2));
        }
    }

    //中序遍历
    public void infixOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        if ((2 * index + 1) < array.length) {
            preOrder((2 * index + 1));
        }
        System.out.print(array[index]);
        if ((2 * index + 2) < array.length) {
            preOrder((2 * index + 2));
        }
    }

    //前序遍历
    public void postOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        if ((2 * index + 1) < array.length) {
            preOrder((2 * index + 1));
        }
        if ((2 * index + 2) < array.length) {
            preOrder((2 * index + 2));
        }
        System.out.print(array[index]);
    }
}
