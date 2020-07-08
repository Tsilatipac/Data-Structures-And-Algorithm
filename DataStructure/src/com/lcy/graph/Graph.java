package com.lcy.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private final ArrayList<String> vertexList;
    private final int[][] edges;
    private  boolean[] isVisited;
    private int numOfEdges;

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        this.numOfEdges = 0;
        isVisited = new boolean[n];
    }

    public static void main(String[] args) {
        int n = 5;
        String[] vertexes = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);

        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }

        graph.insertEdge(0, 1, 1); // A-B
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();
        graph.dfs();
        System.out.println();
        graph.bfs();
    }

    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + " -> ");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    public void bfs(boolean[] isVisited, int i) {
        int u;
        int w;
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getValueByIndex(i) + " -> ");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " -> ");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}



