import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Triconnected Component Structural Validator
 * * Check if a graph is triconnected (3-vertex-connected).
 * * Strategy: Decoupled Monolithic Structural Invariant Verifiers
 * Unifies Biconnected invariants and Induced Subgraph component sweeps natively.
 */
public class TriconnectedComponentCheck {
    private static int time = 0;
    private static boolean hasArticulationPoint = false;

    public static boolean isTriconnected(int vertices, List<List<Integer>> adj) {
        if (vertices < 4) return false;

        if (!isBiconnected(vertices, adj)) return false;

        List<String> separationPairs = findSeparationPairs(vertices, adj);

        return separationPairs.isEmpty();
    }

    private static boolean isBiconnected(int vertices, List<List<Integer>> adj) {
        if (vertices <= 1) return false;
        if (vertices == 2) return adj.get(0).size() == 1;

        time = 0;
        hasArticulationPoint = false;
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];

        int components = 0;
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                components++;
                if (components > 1) return false;
                dfsBcc(i, -1, disc, low, visited, adj);
            }
        }
        return !hasArticulationPoint;
    }

    private static void dfsBcc(int u, int parent, int[] disc, int[] low, boolean[] visited, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                children++;
                dfsBcc(v, u, disc, low, visited, adj);
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

    private static List<String> findSeparationPairs(int vertices, List<List<Integer>> adj) {
        List<String> pairs = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                if (doesRemovalDisconnect(vertices, i, j, adj)) {
                    pairs.add(i + "," + j);
                }
            }
        }
        return pairs;
    }

    private static boolean doesRemovalDisconnect(int n, int u, int v, List<List<Integer>> adj) {
        boolean[] visited = new boolean[n];
        visited[u] = true;
        visited[v] = true;

        int componentCount = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                componentCount++;
                if (componentCount > 1) return true;
                dfsCounter(i, visited, adj);
            }
        }
        return false;
    }

    private static void dfsCounter(int curr, boolean[] visited, List<List<Integer>> adj) {
        visited[curr] = true;
        for (int neighbor : adj.get(curr)) {
            if (!visited[neighbor]) dfsCounter(neighbor, visited, adj);
        }
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(3); adj.get(3).add(2);
        adj.get(3).add(0); adj.get(0).add(3);

        System.out.println("Is graph triconnected? " + isTriconnected(vertices, adj)); // false (contains separation pairs)
    }
}