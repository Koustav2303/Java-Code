import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * PROBLEM: BccDecomposition
 * * Decompose an undirected graph into its maximal biconnected subgraphs (edges grouped into components).
 * * Strategy: Edge Stack Accumulation
 * Run a Tarjan DFS while pushing visited edges onto a LIFO stack. When a cut vertex condition 
 * (low[v] >= disc[u]) matches, pop all edges from the stack down to edge (u, v) to form a distinct BCC.
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */
public class BccDecomposition {
    static class Edge {
        int u, v;
        Edge(int u, int v) { this.u = u; this.v = v; }
        @Override public String toString() { return "(" + u + "-" + v + ")"; }
    }

    private int time = 0;
    private final List<List<Edge>> bccs = new ArrayList<>();
    private final Stack<Edge> edgeStack = new Stack<>();

    public List<List<Edge>> decompose(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, -1, disc, low, visited, adj);
                // Handle any residual loose edges left on the stack
                if (!edgeStack.isEmpty()) {
                    List<Edge> bcc = new ArrayList<>();
                    while (!edgeStack.isEmpty()) bcc.add(edgeStack.pop());
                    bccs.add(bcc);
                }
            }
        }
        return bccs;
    }

    private void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (disc[v] < disc[u]) {
                edgeStack.push(new Edge(u, v)); // Push edge to stack
            }

            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                dfs(v, u, disc, low, visited, adj);
                low[u] = Math.min(low[u], low[v]);

                // If u is a cut vertex relative to v, pop the local component's edges
                if (low[v] >= disc[u]) {
                    List<Edge> bcc = new ArrayList<>();
                    while (true) {
                        Edge e = edgeStack.pop();
                        bcc.add(e);
                        if (e.u == u && e.v == v) break;
                    }
                    bccs.add(bcc);
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

        BccDecomposition decomposer = new BccDecomposition();
        System.out.println("Decomposed BCC Groups: " + decomposer.decompose(vertices, adj));
    }
}