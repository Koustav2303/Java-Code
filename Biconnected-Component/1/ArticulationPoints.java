import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Articulation Points
 * * Find all vertices in an undirected graph whose removal increases the number of connected components.
 * * Strategy: Tarjan's Discovered Time Sieve
 * Use DFS tracking discovery times (disc) and lowest reachable times (low). A vertex u is an articulation point if:
 * 1. It is the root of the DFS tree and has two or more independent children.
 * 2. It is not the root, and it has a child v such that low[v] >= disc[u].
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class ArticulationPoints {
    private int time = 0;

    public List<Integer> findArticulationPoints(int vertices, List<List<Integer>> adj) {
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

    private void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited, boolean[] isAP, List<List<Integer>> adj) {
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

                // Condition 1: Non-root vertex condition
                if (parent != -1 && low[v] >= disc[u]) {
                    isAP[u] = true;
                }
            }
        }

        // Condition 2: Root vertex condition
        if (parent == -1 && children > 1) {
            isAP[u] = true;
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());

        adj.get(1).add(0); adj.get(0).add(1);
        adj.get(0).add(2); adj.get(2).add(0);
        adj.get(2).add(1); adj.get(1).add(2);
        adj.get(0).add(3); adj.get(3).add(0);
        adj.get(3).add(4); adj.get(4).add(0);

        ArticulationPoints apFinder = new ArticulationPoints();
        System.out.println("Articulation Points: " + apFinder.findArticulationPoints(vertices, adj)); // [0, 3]
    }
}