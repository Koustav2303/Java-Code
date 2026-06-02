import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * PROBLEM: Minimum Height Trees
 * * A tree is an undirected graph in which any two vertices are connected by exactly one path.
 * Given a tree of n nodes and a list of edges, you can choose any node as the root.
 * Return a list of all roots that yield a "Minimum Height Tree" (MHT).
 * * Approach:
 * Topological Sort (Peeling Leaves). The roots of an MHT must be the exact centroid(s) of the graph.
 * A graph can have at most 2 centroids.
 * We calculate the degree of all nodes. We add all leaves (degree == 1) to a queue.
 * We remove leaves layer by layer until 1 or 2 nodes are left. These are our centroids.
 */
public class MinimumHeightTrees {
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return List.of(0);
        
        List<List<Integer>> adj = new ArrayList<>();
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.add(i); // Add all leaves
        }
        
        int remainingNodes = n;
        while (remainingNodes > 2) {
            int leavesCount = queue.size();
            remainingNodes -= leavesCount;
            
            for (int i = 0; i < leavesCount; i++) {
                int leaf = queue.poll();
                for (int neighbor : adj.get(leaf)) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.add(neighbor); // New leaf found
                    }
                }
            }
        }
        
        return new ArrayList<>(queue);
    }

    public static void main(String[] args) {
        int[][] edges = {{1,0}, {1,2}, {1,3}};
        System.out.println("MHT Roots: " + findMinHeightTrees(4, edges)); // [1]
    }
}