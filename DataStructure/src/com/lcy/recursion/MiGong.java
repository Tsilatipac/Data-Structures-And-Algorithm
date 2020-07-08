package com.lcy.recursion;

public class MiGong {
    public static final int row = 8;
    public static final int column = 10;

    public static void main(String[] args) {
        int[][] map = new int[row][column];
        for (int i = 0; i < column; i++) {
            map[0][i] = 1;
            map[row - 1][i] = 1;
        }
        for (int i = 0; i < row; i++) {
            map[i][0] = 1;
            map[i][column - 1] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[3][3] = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
        setWay(map, 1, 1);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean setWay(int[][] map, int i, int j) {
        if (map[row-2][column-2] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
