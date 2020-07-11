package com.lcy.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        //创建顶点
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //创建邻接矩阵
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};

        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        //输出
        minTree.showGraph(graph);
        //测试普利姆算法
        minTree.prim(graph, 0);
    }
}

class MinTree {
    /**
     * 创建图的邻接矩阵
     *
     * @param mGraph 图对象
     * @param vertex 图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph mGraph, int vertex, char[] data, int[][] weight) {
        for (int i = 0; i < vertex; i++) {
            mGraph.data[i] = data[i];
            System.arraycopy(weight[i], 0, mGraph.weight[i], 0, vertex);
        }
    }

    public void showGraph(MGraph mGraph) {
        for (int[] link : mGraph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void prim(MGraph graph, int v) {
        int[] visited = new int[graph.vertex];
        Arrays.fill(visited, 0);
        visited[v] = 1;
        int h1 = -1;
        int h2 = -1;
        //将 minWeight 初始成一个大数，后面在遍历过程中，会被替换
        int minWeight = 10000;
        //因为有 graph.vertex顶点，普利姆算法结束后，有 graph.vertex-1边
        for (int k = 1; k < graph.vertex; k++) {
            //这个是确定每一次生成的子图 ，和哪个结点的距离最近
            // i结点表示被访问过的结点
            for (int i = 0; i < graph.vertex; i++) {
                //j结点表示还没有访问过的结点
                for (int j = 0; j < graph.vertex; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将当前这个结点标记为已经访问
            visited[h2] = 1;
            //minWeight 重新设置为最大值 10000
            minWeight = 10000;
        }
    }
}

class MGraph {
    int vertex; //表示图的结点个数
    char[] data; //存放结点数据
    int[][] weight; //存放邻接矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}