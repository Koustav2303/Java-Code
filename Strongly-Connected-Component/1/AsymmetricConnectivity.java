import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Asymmetric Connectivity Check
 * * Determine if an entire directed graph is a single Strongly Connected Component (globally strongly connected).
 * * Strategy: Dual-Pass Reachability Balance
 * Pick an arbitrary vertex (vertex 0). Run a standard DFS pass to verify forward reachability to all other vertices.
 * Reverse all edge directions to generate a transposed graph, reset tracking arrays, and run a second DFS from vertex 0. 
 * If both passes visit every vertex in the graph, the graph is strongly connected.
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E) for the transposed adjacency framework
 */
public class AsymmetricConnectivity {
    public static boolean isStronglyConnected(int vertices, List<List<Integer>> adj) {
        if (vertices <= 1) return true;

        // Pass 1: Forward reachability check from source vertex 0
        boolean[] visited = new boolean[vertices];
        dfs(0, visited, adj);

        for (boolean check : visited) {
            if (!check) return false; // Found a vertex unreachable from 0
        }

        // Generate the transposed matrix layout
        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < vertices; i++) transpose.add(new ArrayList<>());
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                transpose.get(v).add(u);
            }
        }

        // Pass 2: Reverse reachability check from source vertex 0
        boolean[] visitedTranspose = new boolean[vertices];
        dfs(0, visitedTranspose, transpose);

        for (boolean check : visitedTranspose) {
            if (!check) return false; // Found a vertex that cannot reach 0
        }

        return true;
    }

    private static void dfs(int u, boolean[] visited, List<List<Integer>> graph) {
        visited[u] = true;
        for (int v : graph.get(u)) {
            if (!visited[v]) dfs(v, visited, graph);
        }
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(0); // Forms a complete closed directed ring line

        System.out.println("Is graph globally strongly connected? " + isStronglyConnected(vertices, adj)); // true
    }
}