import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Component Cut Weight Analyzer
 * * Given an undirected graph where every vertex has an associated cost/weight value, find the 
 * articulation point (cut vertex) whose deletion yields the lowest vertex weight cost footprint.
 * * Strategy: Natively Embedded Tarjan Sieve
 * Tracks articulation points in-place without external class frame requirements.
 */
public class ComponentCutWeight {
    private static int time = 0;

    public static int findCheapestCutVertex(int vertices, int[] weights, List<List<Integer>> adj) {
        List<Integer> aps = findArticulationPoints(vertices, adj);

        if (aps.isEmpty()) return -1; // System possesses zero cut vertex structural vulnerabilities

        int minimalWeightCost = Integer.MAX_VALUE;
        int targetedCutVertexId = -1;

        for (int node : aps) {
            if (weights[node] < minimalWeightCost) {
                minimalWeightCost = weights[node];
                targetedCutVertexId = node;
            }
        }
        return targetedCutVertexId;
    }

    private static List<Integer> findArticulationPoints(int vertices, List<List<Integer>> adj) {
        time = 0;
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];
        boolean[] isAP = new boolean[vertices];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, -1, disc, low, visited, isAP, adj);
            }
        }

        for (int i = 0; i < vertices; i++) {
            if (isAP[i]) result.add(i);
        }
        return result;
    }

    private static void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited, boolean[] isAP, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                children++;
                dfs(v, u, disc, low, visited, isAP, adj);

                low[u] = Math.min(low[u], low[v]);

                if (parent != -1 && low[v] >= disc[u]) {
                    isAP[u] = true;
                }
            }
        }
        if (parent == -1 && children > 1) {
            isAP[u] = true;
        }
    }

    public static void main(String[] args) {
        int[] weights = {10, 5, 20, 3, 15};
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) adj.add(new ArrayList<>());

        adj.get(1).add(0); adj.get(0).add(1);
        adj.get(0).add(2); adj.get(2).add(0);
        adj.get(2).add(1); adj.get(1).add(2);
        adj.get(0).add(3); adj.get(3).add(0);
        adj.get(3).add(4); adj.get(4).add(0);

        System.out.println("Cheapest Cut Vertex ID: " + findCheapestCutVertex(5, weights, adj)); // 3
    }
}