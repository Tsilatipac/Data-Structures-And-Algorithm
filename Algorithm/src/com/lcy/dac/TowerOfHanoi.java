package com.lcy.dac;

public class TowerOfHanoi {
    public static void main(String[] args) {
        towerOfHanoi(2, 'A', 'B', 'C');
    }

    public static void towerOfHanoi(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第 1 个盘从 " + a + " -> " + c);
        } else {
            // 上面的所有盘从A到B
            towerOfHanoi(num - 1, a, c, b);
            // 最下面的盘从A到C
            System.out.println("第 " + num + " 个盘从 " + a + " -> " + c);
            // B塔到所有盘从B到C
            towerOfHanoi(num - 1, b, a, c);
        }
    }
}
