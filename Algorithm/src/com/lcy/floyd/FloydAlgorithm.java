package com.lcy.floyd;

import java.util.Arrays;

public class FloydAlgorithm {
    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        //创建 Graph 对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        //调用弗洛伊德算法
        graph.floyd();
        graph.show();
    }
}

class Graph {
    private final char[] vertex;
    private final int[][] distance;
    private final int[][] pre;

    /**
     * 构造器
     *
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.distance = matrix;
        this.pre = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            for (int j = 0; j < distance.length; j++) {
                System.out.print("(" + vertex[j] + "到" + vertex[i] + "的最短路径是" + distance[i][j] + ") ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void floyd() {
        int len = 0;
        //对中间顶点对遍历，k是下标
        for (int k = 0; k < distance.length; k++) {
            //从i顶点开始出发
            for (int i = 0; i < distance.length; i++) {
                //到达j顶点
                for (int j = 0; j < distance.length; j++) {
                    len = distance[i][k] + distance[k][j]; //求从i顶点出发，经过k中间顶点，到j顶点距离
                    if (len < distance[i][j]) {
                        distance[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
