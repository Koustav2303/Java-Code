import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * PROBLEM: BccDiameter
 * * Find the maximum diameter (longest shortest path) within each individual biconnected component block of a graph.
 * * Strategy: Isolated Subgraph BFS Multi-Pass
 * Decompose the graph into BCC edge sets using an edge stack. For each component, isolate its induced subgraph layout. 
 * Run an unweighted BFS from each vertex inside the isolated component pool to calculate the max shortest path 
 * bounded strictly within that component's edges.
 * * Complexity:
 * Time Complexity: $O(V \cdot (V + E))$ across component bounds.
 * Space Complexity: $O(V + E)$
 */
public class BccDiameter {
    static class Edge {
        int u, v;
        Edge(int u, int v) { this.u = u; this.v = v; }
    }

    private int time = 0;
    private final Stack<Edge> stack = new Stack<>();
    private final List<List<Edge>> componentsList = new ArrayList<>();

    public void computeDiameters(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) dfs(i, -1, disc, low, visited, adj);
        }

        int compId = 1;
        for (List<Edge> bcc : componentsList) {
            int diameter = calculateBccDiameter(bcc);
            System.out.println("BCC #" + (compId++) + " Local Diameter: " + diameter);
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
                    List<Edge> bcc = new ArrayList<>();
                    while (true) {
                        Edge e = stack.pop();
                        bcc.add(e);
                        if (e.u == u && e.v == v) break;
                    }
                    componentsList.add(bcc);
                }
            }
        }
    }

    private int calculateBccDiameter(List<Edge> edges) {
        Set<Integer> uniqueNodes = new HashSet<>();
        for (Edge e : edges) { uniqueNodes.add(e.u); uniqueNodes.add(e.v); }
        
        int maxNodeIndex = 0;
        for (int node : uniqueNodes) maxNodeIndex = Math.max(maxNodeIndex, node);

        List<List<Integer>> localAdj = new ArrayList<>();
        for (int i = 0; i <= maxNodeIndex; i++) localAdj.add(new ArrayList<>());
        for (Edge e : edges) {
            localAdj.get(e.u).add(e.v);
            localAdj.get(e.v).add(e.u);
        }

        int maxGlobalDiameter = 0;
        for (int startNode : uniqueNodes) {
            int[] dist = new int[maxNodeIndex + 1];
            Arrays.fill(dist, -1);
            Queue<Integer> q = new LinkedList<>();

            dist[startNode] = 0;
            q.add(startNode);

            while (!q.isEmpty()) {
                int curr = q.poll();
                maxGlobalDiameter = Math.max(maxGlobalDiameter, dist[curr]);
                for (int neighbor : localAdj.get(curr)) {
                    if (dist[neighbor] == -1) {
                        dist[neighbor] = dist[curr] + 1;
                        q.add(neighbor);
                    }
                }
            }
        }
        return maxGlobalDiameter;
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2);
        adj.get(2).add(3); adj.get(3).add(2);

        BccDiameter solver = new BccDiameter();
        solver.computeDiameters(vertices, adj);
    }
}