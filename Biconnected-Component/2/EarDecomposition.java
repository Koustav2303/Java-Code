import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Ear Decomposition
 * * Construct an open ear decomposition for an undirected graph. Whitney's theorem states 
 * that a graph is biconnected if and only if it has an open ear decomposition.
 * * Strategy: Back-Edge Paths Extraction
 * Start by finding a simple root cycle to form the first "ear" ($P_0$). 
 * For each subsequent unvisited back-edge, trace its loop path backward to an already-visited 
 * vertex boundary point, generating a series of open paths ("ears") that connect visited vertices.
 */
public class EarDecomposition {
    private int time = 0;
    private final List<List<Integer>> ears = new ArrayList<>();

    public List<List<Integer>> generateEars(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] parent = new int[vertices];
        boolean[] visited = new boolean[vertices];
        boolean[] edgeInspected = new boolean[vertices];

        for (int i = 0; i < vertices; i++) parent[i] = -1;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfsEarTrace(i, -1, disc, parent, visited, edgeInspected, adj);
            }
        }
        return ears;
    }

    private void dfsEarTrace(int u, int p, int[] disc, int[] parent, boolean[] visited, boolean[] inspected, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = ++time;
        parent[u] = p;

        for (int v : adj.get(u)) {
            if (v == p) continue;

            if (!visited[v]) {
                dfsEarTrace(v, u, disc, parent, visited, inspected, adj);
            } else if (disc[v] < disc[u]) {
                // Back-edge found: extract this loop sequence as an independent ear path vector
                List<Integer> ear = new ArrayList<>();
                int curr = u;
                ear.add(v);
                while (curr != v && curr != -1) {
                    ear.add(curr);
                    curr = parent[curr];
                }
                ears.add(ear);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Ear Decomposition generator framework loaded.");
    }
}