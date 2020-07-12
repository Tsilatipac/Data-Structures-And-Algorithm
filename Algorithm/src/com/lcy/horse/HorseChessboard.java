package com.lcy.horse;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {
    private static int X; //棋盘的列数
    private static int Y; //棋盘的列数
    private static boolean[] visited;
    private static boolean finished; //如果为true，表示成功

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1; //马儿初始位置的行，从1开始编号
        int column = 1; //马儿初始位置的列，从1开始编号

        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始值都是false

        Instant start = Instant.now();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        Instant end = Instant.now();
        System.out.println("共耗时: " + Duration.between(start, end).toMillis() + " 毫秒");

        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游问题的算法
     *
     * @param chessboard 棋盘
     * @param row        马儿当前所在位置的行
     * @param column     马儿当前所在位置的列
     * @param step       是第几步，初始位置是第一步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true; //标记该位置已经访问
        ArrayList<Point> points = next(new Point(column, row));
        sort(points);
        while (!points.isEmpty()) {
            Point point = points.remove(0);
            //判断该点是否已经访问过
            if (!visited[point.y * X + point.x]) {
                traversalChessboard(chessboard, point.y, point.x, step + 1);
            }
        }
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    //根据当前的位置，计算能走的位置，放入到集合中
    public static ArrayList<Point> next(Point currentPoint) {
        ArrayList<Point> points = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = currentPoint.x - 2) >= 0 && (p1.y = currentPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x - 1) >= 0 && (p1.y = currentPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x + 1) < X && (p1.y = currentPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x + 2) < X && (p1.y = currentPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x + 2) < X && (p1.y = currentPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x + 1) < X && (p1.y = currentPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x - 1) >= 0 && (p1.y = currentPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x - 2) >= 0 && (p1.y = currentPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }
        return points;
    }

    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(next(o1).size(), next(o2).size());
            }
        });
    }

}
