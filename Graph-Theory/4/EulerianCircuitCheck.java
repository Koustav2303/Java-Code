import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Eulerian Circuit Check
 * * Determine if an undirected connected graph contains an Eulerian Circuit (a closed trail that visits 
 * every single edge exactly once and returns to the starting point).
 * * Strategy: Degree/Connectivity Invariants Check
 * An undirected graph contains an Eulerian Circuit if and only if:
 * 1. Every vertex has an even degree count.
 * 2. All vertices with a degree greater than 0 belong to a single connected component.
 */
public class EulerianCircuitCheck {
    public static boolean hasEulerianCircuit(int vertices, List<List<Integer>> adj) {
        // Condition 1: Verify all nodes have an even degree
        for (int i = 0; i < vertices; i++) {
            if (adj.get(i).size() % 2 != 0) return false;
        }

        // Condition 2: Verify connectivity across active edges
        boolean[] visited = new boolean[vertices];
        int nonZeroDegreeVertex = -1;
        for (int i = 0; i < vertices; i++) {
            if (adj.get(i).size() > 0) {
                nonZeroDegreeVertex = i;
                break;
            }
        }

        if (nonZeroDegreeVertex == -1) return true; // Empty graph has trivial Eulerian status

        dfs(nonZeroDegreeVertex, visited, adj);

        for (int i = 0; i < vertices; i++) {
            if (adj.get(i).size() > 0 && !visited[i]) {
                return false; // Found an active isolated edge component node
            }
        }
        return true;
    }

    private static void dfs(int u, boolean[] visited, List<List<Integer>> adj) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) dfs(v, visited, adj);
        }
    }

    public static void main(String[] args) {
        int vertices = 3;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2); // K3 complete loop triangle

        System.out.println("Contains Eulerian Circuit? " + hasEulerianCircuit(vertices, adj)); // true
    }
}