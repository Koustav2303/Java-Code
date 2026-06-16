import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Bridge Finding
 * * Locate all edges in an undirected graph whose removal disconnects the graph.
 * * Strategy: Low-Time Exclusivity Condition
 * A DFS edge (u, v) is a bridge if and only if the lowest vertex reachable from the child subtree v 
 * cannot reach u or any of its ancestors: low[v] > disc[u].
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class BridgeFinding {
    private int time = 0;

    public List<String> findBridges(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];
        List<String> bridges = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, -1, disc, low, visited, bridges, adj);
            }
        }
        return bridges;
    }

    private void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited, List<String> bridges, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                dfs(v, u, disc, low, visited, bridges, adj);
                low[u] = Math.min(low[u], low[v]);

                // Bridge validation condition check
                if (low[v] > disc[u]) {
                    bridges.add(u + "-" + v);
                }
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(3); adj.get(3).add(2);

        BridgeFinding finder = new BridgeFinding();
        System.out.println("Graph Bridges: " + finder.findBridges(vertices, adj)); // [2-3]
    }
}