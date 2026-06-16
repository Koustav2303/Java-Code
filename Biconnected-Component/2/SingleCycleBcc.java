import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * PROBLEM: Single Cycle BCC Identifier
 * * Identify which biconnected components in an undirected graph consist of exactly 
 * a single simple cycle.
 * * Strategy: Eulerian Degree-2 Invariant
 * Isolate the edges belonging to each individual BCC using an edge stack. 
 * Map these edges into a local sublist map to calculate the vertex degrees within that component. 
 * A biconnected component is a single simple cycle if and only if every vertex in the component 
 * has a local degree of exactly 2.
 */
public class SingleCycleBcc {
    static class Edge { int u, v; Edge(int u, int v) { this.u = u; this.v = v; } }

    private int time = 0;
    private final Stack<Edge> stack = new Stack<>();

    public void analyzeComponents(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) dfs(i, -1, disc, low, visited, adj);
        }
    }

    private void dfs(int u, int p, int[] disc, int[] low, boolean[] visited, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (v == p) continue;
            if (disc[v] < disc[u]) stack.push(new Edge(u, v));

            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                dfs(v, u, disc, low, visited, adj);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] >= disc[u]) {
                    List<Edge> bccEdges = new ArrayList<>();
                    while (true) {
                        Edge e = stack.pop();
                        bccEdges.add(e);
                        if (e.u == u && e.v == v) break;
                    }
                    validateSimpleCycle(bccEdges);
                }
            }
        }
    }

    private void validateSimpleCycle(List<Edge> edges) {
        int maxNode = 0;
        for (Edge e : edges) maxNode = Math.max(maxNode, Math.max(e.u, e.v));

        int[] localDegrees = new int[maxNode + 1];
        Set<Integer> activeNodes = new HashSet<>();

        for (Edge e : edges) {
            localDegrees[e.u]++;
            localDegrees[e.v]++;
            activeNodes.add(e.u);
            activeNodes.add(e.v);
        }

        boolean isSimpleCycle = true;
        for (int node : activeNodes) {
            if (localDegrees[node] != 2) {
                isSimpleCycle = false; // Vertices in a simple cycle must have a degree of exactly 2
                break;
            }
        }
        if (isSimpleCycle) {
            System.out.println("Detected Simple Cycle BCC containing nodes: " + activeNodes);
        }
    }

    public static void main(String[] args) {
        System.out.println("Single Cycle BCC inspection engine fully loaded.");
    }
}