import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * PROBLEM: Bipartite BCC Check
 * * Verify if every biconnected component inside an undirected graph is individually bipartite.
 * * Strategy: Component Color Sieve
 * Extract each BCC using an edge stack. For each component, isolate its induced subgraph 
 * and perform a standard 2-coloring process. If an odd cycle is detected within any BCC, 
 * that component fails the bipartite constraint.
 */
public class BipartiteBccCheck {
    static class Edge { int u, v; Edge(int u, int v) { this.u = u; this.v = v; } }

    private int time = 0;
    private final Stack<Edge> stack = new Stack<>();
    private boolean allBccBipartite = true;

    public boolean checkGraph(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) dfs(i, -1, disc, low, visited, adj);
        }
        return allBccBipartite;
    }

    private void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (v == parent) continue;
            if (disc[v] < disc[u]) stack.push(new Edge(u, v));

            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                dfs(v, u, disc, low, visited, adj);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] >= disc[u]) {
                    List<Edge> componentEdges = new ArrayList<>();
                    while (true) {
                        Edge e = stack.pop();
                        componentEdges.add(e);
                        if (e.u == u && e.v == v) break;
                    }
                    if (!isComponentBipartite(componentEdges)) {
                        allBccBipartite = false;
                    }
                }
            }
        }
    }

    private boolean isComponentBipartite(List<Edge> edges) {
        // Build a temporary localized lookup adjacency matrix map
        int maxNode = 0;
        for (Edge e : edges) {
            maxNode = Math.max(maxNode, Math.max(e.u, e.v));
        }
        List<List<Integer>> localAdj = new ArrayList<>();
        for (int i = 0; i <= maxNode; i++) localAdj.add(new ArrayList<>());
        
        for (Edge e : edges) {
            localAdj.get(e.u).add(e.v);
            localAdj.get(e.v).add(e.u);
        }

        int[] colors = new int[maxNode + 1];
        Arrays.fill(colors, -1);

        int seed = edges.get(0).u;
        colors[seed] = 0;

        // Run local BFS verification loop frame
        Stack<Integer> q = new Stack<>();
        q.push(seed);

        while (!q.isEmpty()) {
            int curr = q.pop();
            for (int neighbor : localAdj.get(curr)) {
                if (colors[neighbor] == -1) {
                    colors[neighbor] = 1 - colors[curr];
                    q.push(neighbor);
                } else if (colors[neighbor] == colors[curr]) {
                    return false; // Odd cycle detected within this component
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Bipartite BCC evaluation script compiled successfully.");
    }
}