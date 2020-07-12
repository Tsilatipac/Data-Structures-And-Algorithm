package com.lcy.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //创建 Graph对象
        Graph graph = new Graph(vertex, matrix);
        //测试, 看看图的邻接矩阵是否ok
        graph.showGraph();
        //测试迪杰斯特拉算法
        graph.dijkstra(6);
        graph.showDijkstra();
    }

}

class Graph {
    private final char[] vertexes;
    private final int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertexes, int[][] matrix) {
        this.vertexes = vertexes;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] links : matrix) {
            System.out.println(Arrays.toString(links));
        }
    }

    //迪杰斯特拉算法的实现
    public void dijkstra(int index) {
        vv = new VisitedVertex(vertexes.length, index);
        update(index);
        for (int i = 1; i < vertexes.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    private void update(int index) {
        int len;
        for (int i = 0; i < matrix[index].length; i++) {
            len = vv.getDis(index) + matrix[index][i];
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updatePre(i, index);
                vv.updateDis(i, len);
            }
        }
    }

    public void showDijkstra() {
        vv.show();
    }
}

class VisitedVertex {

    public int[] already_arr;
    public int[] pre_visited;
    public int[] dis;

    /**
     * @param length 顶点个数
     * @param index  出发顶点的下标
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];

        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;
    }

    /**
     * 判断index是否被访问过
     *
     * @param index 数组下标
     * @return 访问过为true，否则false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    //更新出发点到index顶点的距离
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    //更新顶点的前驱为index结点
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    //返回出发顶点到index顶点的距离
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并访问新的顶点
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    public void show() {
        System.out.println("================");
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }

}