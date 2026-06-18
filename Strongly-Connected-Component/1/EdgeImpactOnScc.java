import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * PROBLEM: Edge Impact on SCC Count
 * * Identify which single edge removals increase the total number of Strongly Connected Components in a graph.
 * * Strategy: Simulation Component Count Comparisons
 * Count the baseline number of SCCs using Kosaraju's two-pass algorithm. 
 * For each edge in the graph, simulate its removal, re-evaluate the SCC decomposition count over the modified structure, 
 * and log the edge if the component count increases.
 * * Complexity:
 * Time Complexity: O(E * (V + E))
 */
public class EdgeImpactOnScc {
    static class DirectedEdge {
        int src, dest;
        DirectedEdge(int s, int d) { this.src = s; this.dest = d; }
    }

    public static List<String> findCriticalEdges(int vertices, List<DirectedEdge> edges) {
        List<String> criticalEdges = new ArrayList<>();
        int baseCount = countSccs(vertices, edges, null);

        for (DirectedEdge edgeToRemove : edges) {
            int simulatedCount = countSccs(vertices, edges, edgeToRemove);
            if (simulatedCount > baseCount) {
                criticalEdges.add(edgeToRemove.src + "->" + edgeToRemove.dest);
            }
        }
        return criticalEdges;
    }

    private static int countSccs(int vertices, List<DirectedEdge> edges, DirectedEdge skipEdge) {
        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
            transpose.add(new ArrayList<>());
        }

        for (DirectedEdge e : edges) {
            if (skipEdge != null && e.src == skipEdge.src && e.dest == skipEdge.dest) continue;
            adj.get(e.src).add(e.dest);
            transpose.get(e.dest).add(e.src);
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) fillOrder(i, visited, stack, adj);
        }

        int sccCount = 0;
        boolean[] visited2 = new boolean[vertices];
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!visited2[curr]) {
                dfsCollect(curr, visited2, transpose);
                sccCount++;
            }
        }
        return sccCount;
    }

    private static void fillOrder(int u, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
        visited[u] = true;
        for (int v : adj.get(u)) if (!visited[v]) fillOrder(v, visited, stack, adj);
        stack.push(u);
    }

    private static void dfsCollect(int u, boolean[] visited, List<List<Integer>> transpose) {
        visited[u] = true;
        for (int v : transpose.get(u)) if (!visited[v]) dfsCollect(v, visited, transpose);
    }

    public static void main(String[] args) {
        int vertices = 3;
        List<DirectedEdge> edges = new ArrayList<>();
        edges.add(new DirectedEdge(0, 1));
        edges.add(new DirectedEdge(1, 2));
        edges.add(new DirectedEdge(2, 0)); // Closed loop triangle configuration

        System.out.println("Critical Edge Break candidates: " + findCriticalEdges(vertices, edges)); // Every edge breaks the loop
    }
}