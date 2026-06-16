import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Biconnected Graph Check
 * * Determine if an entire undirected graph is fully biconnected, meaning it is completely connected 
 * and contains absolutely no articulation points.
 * * Strategy: Multi-Condition Invariant Check
 * A graph is biconnected if and only if:
 * 1. The total number of connected components is exactly 1.
 * 2. The number of discovered articulation points is exactly 0.
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class BiconnectedGraphCheck {
    private int time = 0;
    private boolean hasArticulationPoint = false;

    public boolean isBiconnected(int vertices, List<List<Integer>> adj) {
        if (vertices <= 1) return false;
        if (vertices == 2) return adj.get(0).size() == 1; // Direct edge check for small graph

        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];

        // Step 1: Ensure the graph is globally connected
        int components = 0;
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                components++;
                if (components > 1) return false; // Early exit: disconnected graph
                dfs(i, -1, disc, low, visited, adj);
            }
        }

        // Step 2: Return confirmation based on internal articulation status flags
        return !hasArticulationPoint;
    }

    private void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                children++;
                dfs(v, u, disc, low, visited, adj);
                low[u] = Math.min(low[u], low[v]);

                if (parent != -1 && low[v] >= disc[u]) {
                    hasArticulationPoint = true;
                }
            }
        }
        if (parent == -1 && children > 1) {
            hasArticulationPoint = true;
        }
    }

    public static void main(String[] args) {
        int vertices = 3;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2); // Complete triangle graph K3

        BiconnectedGraphCheck checker = new BiconnectedGraphCheck();
        System.out.println("Is graph fully biconnected? " + checker.isBiconnected(vertices, adj)); // true
    }
}