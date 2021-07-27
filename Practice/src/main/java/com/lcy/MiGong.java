package com.lcy;

public class MiGong {
    private static final int WIDTH = 8;
    private static final int HEIGHT = 7;

    public static void main(String[] args) {
        int[][] map = new int[WIDTH][HEIGHT];

        // 围墙
        for (int i = 0; i < HEIGHT; i++) {
            map[0][i] = 1;
            map[WIDTH - 1][i] = 1;
        }
        for (int i = 0; i < WIDTH; i++) {
            map[i][0] = 1;
            map[i][HEIGHT - 1] = 1;
        }

        // 挡板
        map[3][1] = 1;
        map[3][2] = 1;

        setWay(map, 1, 1);

        printMap(map);
    }

    private static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        }

        if (map[i][j] == 0) {
            map[i][j] = 2;
            if (setWay(map, i -1, j)) {
                return true;
            } else if (setWay(map, i +1, j)) {
                return true;
            } else if (setWay(map, i, j + 1)) {
                return true;
            } else if (setWay(map, i, j - 1)) {
                return true;
            } else {
                map[i][j] = 3;
                return false;
            }
        }
        return false;
    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
