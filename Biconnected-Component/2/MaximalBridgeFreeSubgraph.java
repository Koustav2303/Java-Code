import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PROBLEM: Maximal Bridge-Free Subgraph
 * * Isolate and extract the single largest induced subgraph configuration that contains 
 * absolutely zero bridges (equivalent to the largest 2-edge-connected component).
 * * Strategy: Bridge Sieve Isolation
 * 1. Find all critical network bridges using Tarjan's edge timestamp inequalities.
 * 2. Run a secondary DFS pass to group vertices into components, refusing to cross any bridge edges.
 * 3. Track and return the component that contains the largest number of vertices.
 */
public class MaximalBridgeFreeSubgraph {
    private int time = 0;
    private final Set<String> bridges = new HashSet<>();

    public int findMaximalSubgraphSize(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];

        // Step 1: Detect and index all network bridge bottlenecks
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) dfsBridges(i, -1, disc, low, visited, adj);
        }

        // Step 2: Traverse and group vertices into component clusters
        boolean[] visitedComponents = new boolean[vertices];
        int maxSize = 0;

        for (int i = 0; i < vertices; i++) {
            if (!visitedComponents[i]) {
                List<Integer> component = new ArrayList<>();
                dfsCollect(i, visitedComponents, component, adj);
                maxSize = Math.max(maxSize, component.size());
            }
        }
        return maxSize;
    }

    private void dfsBridges(int u, int p, int[] disc, int[] low, boolean[] visited, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        for (int v : adj.get(u)) {
            if (v == p) continue;
            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                dfsBridges(v, u, disc, low, visited, adj);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    bridges.add(u + "-" + v);
                    bridges.add(v + "-" + u);
                }
            }
        }
    }

    private void dfsCollect(int u, boolean[] visited, List<Integer> comp, List<List<Integer>> adj) {
        visited[u] = true;
        comp.add(u);
        for (int v : adj.get(u)) {
            if (visited[v] || bridges.contains(u + "-" + v)) continue;
            dfsCollect(v, visited, comp, adj);
        }
    }

    public static void main(String[] args) {
        System.out.println("Maximal Bridge-Free subgraph system tracking module deployed.");
    }
}