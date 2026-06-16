import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * PROBLEM: BccSizeDistribution
 * * Decompose a graph and compute the exact structural distribution (vertex and edge counts) 
 * of every single Biconnected Component (BCC).
 * * Strategy: Stack Frame Metric Extraction
 * Run a Tarjan depth-first search (DFS) loop using an edge stack. When a cut-vertex bottleneck 
 * condition matches, pop all edges belonging to the local component. Unpack these edges 
 * into a temporary Set to count unique vertices alongside the raw edge count.
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */
public class BccSizeDistribution {
    static class Edge {
        int u, v;
        Edge(int u, int v) { this.u = u; this.v = v; }
    }

    private int time = 0;
    private final Stack<Edge> stack = new Stack<>();

    public void printDistribution(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];

        int componentId = 1;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, -1, disc, low, visited, adj, componentId);
            }
        }
    }

    private void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited, List<List<Integer>> adj, int compId) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (disc[v] < disc[u]) {
                stack.push(new Edge(u, v));
            }

            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                dfs(v, u, disc, low, visited, adj, compId);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] >= disc[u]) {
                    Set<Integer> uniqueVertices = new HashSet<>();
                    int edgeCount = 0;
                    
                    while (true) {
                        Edge e = stack.pop();
                        edgeCount++;
                        uniqueVertices.add(e.u);
                        uniqueVertices.add(e.v);
                        if (e.u == u && e.v == v) break;
                    }
                    System.out.println("BCC #" + (compId++) + " -> Vertices: " + uniqueVertices.size() + ", Edges: " + edgeCount);
                }
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2);
        adj.get(2).add(3); adj.get(3).add(2);

        BccSizeDistribution dist = new BccSizeDistribution();
        dist.printDistribution(vertices, adj);
    }
}