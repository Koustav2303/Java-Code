import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PROBLEM: Non-Separating Vertex Finder
 * * Locate and extract all non-separating vertices (non-cut vertices) in a connected undirected graph.
 * * Strategy: Fixed Set Utility Imports & Embedded Tarjan Pass
 * Resolves missing java.util class references alongside explicit AP filters.
 */
public class NonSeparatingVertex {
    private static int time = 0;

    public static List<Integer> findNonSeparatingVertices(int vertices, List<List<Integer>> adj) {
        List<Integer> cutVertices = findArticulationPoints(vertices, adj);
        
        Set<Integer> apSet = new HashSet<>(cutVertices);
        List<Integer> nonSeparatingVertices = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            if (!apSet.contains(i)) {
                nonSeparatingVertices.add(i);
            }
        }
        return nonSeparatingVertices;
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
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) adj.add(new ArrayList<>());
        adj.get(1).add(0); adj.get(0).add(1);
        adj.get(0).add(2); adj.get(2).add(0);
        adj.get(2).add(1); adj.get(1).add(2);
        adj.get(0).add(3); adj.get(3).add(0);
        adj.get(3).add(4); adj.get(4).add(0);

        System.out.println("Non-Separating Vertices: " + findNonSeparatingVertices(5, adj)); // [1, 2, 4]
    }
}