import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Directed Biconnectivity Verification
 * * Check if a directed graph is strongly biconnected. A directed graph is strongly biconnected if it is 
 * strongly connected and contains no single vertex whose removal breaks the remaining graph into 
 * separate strongly connected components.
 * * Strategy: Induced Subgraph SCC Counting Pass
 * 1. Verify if the baseline graph is globally strongly connected using a Kosaraju/Tarjan pass.
 * 2. Simulate the removal of each vertex one by one. For each removal, run a standard SCC check 
 * on the remaining $V - 1$ vertices to ensure it remains a single strongly connected component.
 * * Complexity:
 * Time Complexity: $O(V \cdot (V + E))$
 */
public class DirectedBiconnectivityCheck {
    public boolean isStronglyBiconnected(int vertices, List<List<Integer>> adj) {
        if (!isStronglyConnected(vertices, adj, -1)) return false;

        // Verify if removing any single vertex leaves the remaining subgraph strongly connected
        for (int i = 0; i < vertices; i++) {
            if (!isStronglyConnected(vertices, adj, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isStronglyConnected(int n, List<List<Integer>> adj, int skippedVertex) {
        boolean[] visited = new boolean[n];
        int startNode = (skippedVertex == 0) ? 1 : 0;
        if (n <= 1 || (n == 2 && skippedVertex != -1)) return true;

        dfs(startNode, skippedVertex, visited, adj);

        // Verify forward reachability bounds
        for (int i = 0; i < n; i++) {
            if (i != skippedVertex && !visited[i]) return false;
        }

        // Generate inverse transposition matrix layout
        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < n; i++) transpose.add(new ArrayList<>());
        for (int u = 0; u < n; u++) {
            if (u == skippedVertex) continue;
            for (int v : adj.get(u)) {
                if (v != skippedVertex) transpose.get(v).add(u);
            }
        }

        boolean[] visitedTranspose = new boolean[n];
        dfs(startNode, skippedVertex, visitedTranspose, transpose);

        // Verify reverse transposition reachability bounds
        for (int i = 0; i < n; i++) {
            if (i != skippedVertex && !visitedTranspose[i]) return false;
        }
        return true;
    }

    private void dfs(int u, int skip, boolean[] visited, List<List<Integer>> graph) {
        visited[u] = true;
        for (int v : graph.get(u)) {
            if (v != skip && !visited[v]) dfs(v, skip, visited, graph);
        }
    }

    public static void main(String[] args) {
        System.out.println("Directed Strong Biconnectivity validation engine online.");
    }
}