import java.util.Arrays;

/**
 * PROBLEM: Redundant Connection II
 * * A directed graph is a rooted tree if it has exactly one vertex with no incoming edges (the root), 
 * and all other vertices have exactly one incoming edge.
 * Given a directed graph that started as a rooted tree with n nodes (labeled 1 to n), but one 
 * additional directed edge was added. Return an edge that can be removed so that the resulting 
 * graph is a valid rooted tree.
 * * Strategy: Multi-Constraint Edge Caching
 * In a directed graph, an extra edge can cause two issues: a node with two parents (in-degree == 2) 
 * or a directed cycle. 
 * Track incoming parents. If a node gets a second parent, cache those two edges and skip adding the 
 * second one to the DSU. Run the DSU loop. If a cycle is still detected, the edge inside the cycle is the issue.
 */
public class RedundantConnectionII {
    static class DSU {
        int[] parent;
        public DSU(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
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

    public static int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parentTracker = new int[n + 1];
        int[] edge1 = null;
        int[] edge2 = null;

        // Step 1: Check for any node possessing two parent references
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (parentTracker[v] != 0) {
                edge1 = new int[]{parentTracker[v], v};
                edge2 = new int[]{u, v};
                break;
            }
            parentTracker[v] = u;
        }

        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            // Temporarily skip processing the second parent edge duplicate candidate
            if (edge2 != null && Arrays.equals(edge, edge2)) continue;

            if (!dsu.union(edge[0], edge[1])) {
                // If a cycle is detected and we found a two-parent node, edge1 caused the issue upstream
                if (edge1 != null) return edge1;
                return edge; // Otherwise, this edge directly completed a pure cycle loop
            }
        }
        return edge2;
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println("Redundant edge: " + Arrays.toString(findRedundantDirectedConnection(edges))); // [2, 3]
    }
}