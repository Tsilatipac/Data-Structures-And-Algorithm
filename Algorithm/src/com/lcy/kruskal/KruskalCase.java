package com.lcy.kruskal;

import java.util.Arrays;

public class KruskalCase {

    private static final int INF = Integer.MAX_VALUE; //INF表示两个顶点不能联通
    private int edgeNums; //边的个数
    private char[] vertexes; //顶点数组
    private int[][] matrix; //邻接矩阵

    public KruskalCase(char[] vertexes, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexes.length;

        //初始化顶点和边
        this.vertexes = new char[vlen];
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vertexes.length; i++) {
            this.vertexes[i] = vertexes[i];
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, vlen);
        }

        //统计边的数量
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNums++;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        //创建KruskalCase 对象实例
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        //输出构建的
        kruskalCase.print();
        kruskalCase.kruskal();
    }


    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private void kruskal() {
        //表示最后结果数组的索引
        int index = 0;
        //用于"已有最小生成树"中的每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNums];
        //保存最后的最小生成树
        EData[] results = new EData[edgeNums];

        //获取图中所有边的集合
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + "共" + edges.length);

        //按照边的权值从小到达排序
        sortEdge(edges);

        //遍历edges 数组，将边添加到最小生成树中时，判断是准备加入的边否形成了回路，如果没有，就加入 results, 否则不能加入
        for (int i = 0; i < edgeNums; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                ends[m] = n;
                results[index++] = edges[i];
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.println(results[i]);
        }
    }

    /**
     * 对边进行排序，冒泡排序
     *
     * @param edges 边的集合
     */
    private void sortEdge(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 根据顶点的值返回顶点对应的下表
     *
     * @param ch 顶点的值
     * @return 顶点对应的下表
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，通过matrix邻接矩阵获取
     *
     * @return 图中所有边的数组
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNums];
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于判断两个顶点的终点是否相同
     *
     * @param ends 记录了各个顶点对应的终点是哪个，ends在遍历过程中逐渐形成
     * @param i    表示传入的顶点对应的下标
     * @return 下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

class EData {

    char start; //边的一个点
    char end; //边的另外一个点
    int weight; //边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}