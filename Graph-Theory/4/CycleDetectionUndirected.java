import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Cycle Detection Undirected
 * * Check if an undirected graph contains at least one cycle loop configuration.
 * * Strategy: Parent Element Tracking DFS
 * Run a DFS traversal tracking the "parent" node that triggered the current recursive call. 
 * If a neighbor is already visited and is NOT the immediate parent of the current node, 
 * a cross-edge exists, confirming a cycle loop.
 */
public class CycleDetectionUndirected {
    public static boolean hasCycle(int vertices, List<List<Integer>> adj) {
        boolean[] visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (dfsCheck(i, -1, visited, adj)) return true;
            }
        }
        return false;
    }

    private static boolean dfsCheck(int curr, int parent, boolean[] visited, List<List<Integer>> adj) {
        visited[curr] = true;
        for (int neighbor : adj.get(curr)) {
            if (!visited[neighbor]) {
                if (dfsCheck(neighbor, curr, visited, adj)) return true;
            } else if (neighbor != parent) {
                return true; // Visited node that isn't the parent means a cycle is closed
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int vertices = 3;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2); // Complete triangle graph

        System.out.println("Does undirected graph possess cycles? " + hasCycle(vertices, adj)); // true
    }
}