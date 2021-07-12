package com.thorin.graph;

/**
 * 最小生成树-克鲁斯卡尔算法
 * 克鲁斯卡尔算法：
 *  普里姆算法是从某一顶点为起点，逐步找各个顶点最小权值的边来构成最小生成树。
 *  那我们也可以直接从边出发，寻找权值最小的边来构建最小生成树。不过在构建的过程中要考虑是否会形成环的情况
 * 基本思想：
 *  首先构造一个只含有n个顶点的森林，然后依权值从小到大从连通网中选择加入到森林中，并且森林中不产生回路，直到森林变成一棵树为止
 */
public class Kruskal {

    // 边的个数
    private int edgeNum;
    // 顶点
    private String[] data;
    // 权值
    private int[][] vertex;
    // INF标记边之间不连通
    private static final int INF = Integer.MAX_VALUE;

    public Kruskal(String[] data, int[][] vertex) {
        int len = data.length;
        this.data = new String[len];
        this.vertex = vertex;
        for (int i = 0; i < len; i++) {
            this.data[i] = data[i];
        }
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex[0].length; j++) {
                this.vertex[i][j] = vertex[i][j];
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (vertex[i][j] != INF) {
                    edgeNum ++;
                }
            }
        }
    }

    static class EdgeData {
        // 边的起点
        String start;
        // 边的终点
        String end;
        // 边的权值
        int weight;

        public EdgeData(String start, String end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString(){
            return "EdgeData<" + start + "," + end + ">=" + weight;
        }
    }

    // 对边按权值进行排序
    private void sortEdges(EdgeData[] edgeData){
        for (int i = 0; i < edgeData.length -1; i++) {
            for (int j = 0; j < edgeData.length - 1; j++) {
                if (edgeData[j].weight > edgeData[j + 1].weight) {
                    EdgeData temp = edgeData[j + 1];
                    edgeData[j + 1] = edgeData[j];
                    edgeData[j] = temp;
                }
            }
        }
    }

    // 返回顶点对应的下标
    private int getPosition(String data){
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    // 获取图中的边，存放在数组中
    private EdgeData[] getEdges(){
        int index = 0;
        EdgeData[] edges = new EdgeData[edgeNum];
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (vertex[i][j] != INF) {
                    edges[index++] = new EdgeData(this.data[i], this.data[j], vertex[i][j]);
                }
            }
        }
        return edges;
    }

    // 获取下标为i的顶点的终点
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal(){
        // 最后结果数组的索引
        int index = 0;
        // 保存最小生成树中顶点对应的终点的下标
        int[] ends = new int[edgeNum];
        // 用于保存最小生成树的结果
        EdgeData[] result = new EdgeData[edgeNum];
        // 获取图中所有的边
        EdgeData[] edges  = getEdges();
        // 从小到大排序边
        sortEdges(edges);
        // 遍历edges，将边添加到最小生成树
        for (int i = 0; i < edgeNum; i++) {
            // 获取边的起点
            int p1 = getPosition(edges[i].start);
            // 获取边的终点
            int p2 = getPosition(edges[i].end);
            // 获取p1在最小生成树中的终点
            int m = getEnd(ends, p1);
            // 获取p2在最小生成树中的终点
            int n = getEnd(ends, p2);
            // 判断p1和p2是否构成回路
            if (m != n) {
                // 设置m在最小生成树中的终点
                ends[m] = n;
                // 将一条边加入数组
                result[index++] = edges[i];
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.println(result[i]);
        }
    }

    public static void main(String[] args) {
        String[] data = {"A", "B", "C", "D", "E", "F", "G"};
        int[][] weight = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        Kruskal kruskal = new Kruskal(data, weight);
        kruskal.kruskal();
    }
}
