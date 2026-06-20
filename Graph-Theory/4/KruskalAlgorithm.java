import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PROBLEM: Kruskal's Algorithm
 * * Find the Minimum Spanning Tree (MST) of a connected, undirected graph with weighted edges.
 * * Strategy: Disjoint Set Cycle Filter
 * Sort all edges in ascending order of weight. Iterate through the sorted edges, using a Union-Find 
 * (Disjoint Set) data structure to check if the endpoints belong to the same component. 
 * If they don't, add the edge to the MST and merge the components, preventing cycle formations.
 * * Complexity:
 * Time Complexity: O(E * log E) due to sorting operations.
 */
public class KruskalAlgorithm {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int s, int d, int w) { this.src = s; this.dest = d; this.weight = w; }
        @Override public int compareTo(Edge o) { return Integer.compare(this.weight, o.weight); }
    }

    static class DisjointSet {
        int[] parent, rank;
        DisjointSet(int n) {
            parent = new int[n]; rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]); // Path compression optimization
        }
        boolean union(int i, int j) {
            int rootI = find(i); int rootJ = find(j);
            if (rootI == rootJ) return false;
            if (rank[rootI] < rank[rootJ]) parent[rootI] = rootJ;
            else if (rank[rootI] > rank[rootJ]) parent[rootJ] = rootI;
            else { parent[rootJ] = rootI; rank[rootI]++; }
            return true;
        }
    }

    public static List<Edge> computeMST(int vertices, List<Edge> edges) {
        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(vertices);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            if (ds.union(edge.src, edge.dest)) {
                mst.add(edge);
                if (mst.size() == vertices - 1) break; // Finished building MST skeleton framework
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(1, 2, 5)); // Selected over weight 10 edge to link components

        List<Edge> result = computeMST(3, edges);
        System.out.println("Total edges inside Kruskal MST: " + result.size()); // 2
    }
}