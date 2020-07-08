package com.lcy.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        for (int i = 1; i < 6; i++) {
            stack.push(i);
        }
        stack.list();
//        for (int i = 1; i < 7; i++) {
//            System.out.println(stack.pop());;
//        }
    }
}

class ArrayStack {
    private final int maxSize;
    private final int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    public int pop() {
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        return stack[top--];
    }

    public void list(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        for (int i = top; i >=0 ; i--) {
            System.out.println(stack[i]);
        }
    }
}