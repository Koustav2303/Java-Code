import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROBLEM: Critical Connections
 * * Given an undirected network setup graph, return all critical paths (bridges) formatted 
 * as standard nested integer list layouts.
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */
public class CriticalConnections {
    private int time = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> edge : connections) {
            adj.get(edge.get(0)).add(edge.get(1));
            adj.get(edge.get(1)).add(edge.get(0));
        }

        int[] disc = new int[n];
        int[] low = new int[n];
        boolean[] visited = new boolean[n];
        List<List<Integer>> result = new ArrayList<>();

        dfs(0, -1, disc, low, visited, result, adj);
        return result;
    }

    private void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited, List<List<Integer>> result, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                dfs(v, u, disc, low, visited, result, adj);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    result.add(Arrays.asList(u, v));
                }
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> conn = new ArrayList<>();
        conn.add(Arrays.asList(0, 1)); conn.add(Arrays.asList(1, 2)); conn.add(Arrays.asList(2, 0)); conn.add(Arrays.asList(1, 3));
        CriticalConnections solver = new CriticalConnections();
        System.out.println("Critical System connections: " + solver.criticalConnections(4, conn)); // [[1, 3]]
    }
}