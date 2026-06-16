import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: DFS Tree Edge Classifier
 * * Classify every edge in an undirected graph into either a "Tree Edge" (structural discovery path) 
 * or a "Back Edge" (cycle loop signature) during a Tarjan DFS pass.
 * * Strategy: Neighborhood Polarity Flags
 * Traverse using standard discovery timestamp indices. For any edge (u, v):
 * - If v is unvisited, the traversal step forms a valid **Tree Edge**.
 * - If v is already visited and is not the immediate parent of u, it forms a **Back Edge**.
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */
public class DfsTreeEdgeClassifier {
    private int time = 0;

    public void classifyEdges(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, -1, disc, visited, adj);
            }
        }
    }

    private void dfs(int u, int parent, int[] disc, boolean[] visited, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = ++time;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (!visited[v]) {
                System.out.println("Edge (" + u + " -> " + v + ") is a Tree Edge");
                dfs(v, u, disc, visited, adj);
            } else if (disc[v] < disc[u]) {
                // Ensure the back edge is only printed once for undirected tracking pairs
                System.out.println("Edge (" + u + " -> " + v + ") is a Back Edge");
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 3;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2);

        DfsTreeEdgeClassifier classifier = new DfsTreeEdgeClassifier();
        classifier.classifyEdges(vertices, adj);
    }
}