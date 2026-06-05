import java.util.*;

/**
 * PROBLEM: Minimum Spanning Tree (Kruskal's Algorithm)
 * * Given an undirected, connected, weighted graph with vertices V and edges E, 
 * find the sum of weights of the edges in the Minimum Spanning Tree.
 * * Strategy: Greedy Edge Selection
 * Sort all edges in non-decreasing order of their weights. Iterate through the sorted edges 
 * and perform a union operation on the connected nodes. If union succeeds, add the edge weight 
 * to the MST total. If they already share a root, skip to prevent a cycle.
 */
public class KruskalMST {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        public Edge(int s, int d, int w) { src = s; dest = d; weight = w; }
        public int compareTo(Edge compareEdge) { return this.weight - compareEdge.weight; }
    }

    static class DSU {
        int[] parent;
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public boolean union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                return true;
            }
            return false;
        }
    }

    public static int getMSTWeight(int vertices, List<Edge> edges) {
        Collections.sort(edges); // Greedy sort condition
        DSU dsu = new DSU(vertices);
        int mstWeight = 0;
        int edgesCount = 0;

        for (Edge edge : edges) {
            if (dsu.union(edge.src, edge.dest)) {
                mstWeight += edge.weight;
                edgesCount++;
                if (edgesCount == vertices - 1) break;
            }
        }
        return mstWeight;
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
            new Edge(0, 1, 10), new Edge(0, 2, 6), new Edge(0, 3, 5),
            new Edge(1, 3, 15), new Edge(2, 3, 4)
        );
        System.out.println("Minimum Spanning Tree total cost: " + getMSTWeight(4, edges)); // 19
    }
}