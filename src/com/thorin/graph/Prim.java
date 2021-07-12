package com.thorin.graph;

/**
 * 最小生成树-普利姆算法
 * 最下生成树：
 *  最小生成树是原图的最小连通子图，也就是说该子图是连通的并且没有多余的边，更不会形成回路。
 *  最重要的是最小生成树的所有边的权值相加最小，这也是最小生成树的来源
 * 普利姆算法：
 *  主要思路就是从候选节点中选择最小的权值添加到最小生成树中
 */
public class Prim {

    static class Graph{
        int vertex;
        String[] data;
        int[][] weight;

        public Graph(int vertex){
            this.vertex = vertex;
            this.data = new String[vertex];
            this.weight = new int[vertex][vertex];
        }
    }

    public void prim(Graph graph, int v) {
        // 标记节点是否被访问过，被访问过用1标记
        int[] visited = new int[graph.vertex];
        // 把当前节点标记为已访问
        visited[v] = 1;
        // x和y用来标记加入最小生成树中的节点坐标
        int x = -1;
        int y = -1;
        // 10000表示两个点不连通
        int minWeight = 10000;
        // 有graph.vertex个顶点，需要生成graph.vertex-1条边
        for (int i = 1; i < graph.vertex; i++) {
            for (int j = 0; j < graph.vertex; j++) {
                for (int k = 0; k < graph.vertex; k++) {
                    if (visited[j] == 1 && visited[k] == 0 && graph.weight[j][k] < minWeight) {
                        minWeight = graph.weight[j][k];
                        x = j;
                        y = k;
                    }
                }
            }
            // 找到一条最小边
            System.out.println("边<" + graph.data[x] + "," + graph.data[y] + ">权值: " + minWeight);
            // 将节点标记为已访问过
            visited[y] = 1;
            // 重置minWeight
            minWeight = 10000;
        }
    }

    public static void main(String[] args) {
        String[] data = {"A", "B", "C", "D", "E", "F", "G"};
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        Graph graph = new Graph(data.length);
        graph.data = data;
        graph.weight = weight;

        Prim prim = new Prim();
        prim.prim(graph, 0);
    }
}
