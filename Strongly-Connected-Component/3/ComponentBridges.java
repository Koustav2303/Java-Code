import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * PROBLEM: Component Bridges
 * * Identify all directed edges in a graph that connect two different Strongly Connected Components, 
 * and whose removal increases the number of weakly connected components in the condensed graph.
 * * Strategy: Condensed DAG Bridge Tracking
 * Decompose the graph using a two-pass finishing sort to resolve component IDs. 
 * Construct the condensed DAG. Run a standard bridge-finding algorithm on the underlying un-directed 
 * representation of the condensed graph to locate critical inter-component transition paths.
 */
public class ComponentBridges {
    private int time = 0;

    public List<String> findComponentBridges(int vertices, List<List<Integer>> adj) {
        int[] compMap = getComponentMap(vertices, adj);
        int sccCount = 0;
        for (int id : compMap) sccCount = Math.max(sccCount, id + 1);

        List<List<Integer>> condensedUndirected = new ArrayList<>();
        for (int i = 0; i < sccCount; i++) condensedUndirected.add(new ArrayList<>());

        // Construct the underlying un-directed skeleton of the condensed DAG
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                if (compMap[u] != compMap[v]) {
                    condensedUndirected.get(compMap[u]).add(compMap[v]);
                    condensedUndirected.get(compMap[v]).add(compMap[u]);
                }
            }
        }

        List<String> bridges = new ArrayList<>();
        int[] disc = new int[sccCount];
        int[] low = new int[sccCount];
        boolean[] visited = new boolean[sccCount];
        Arrays.fill(disc, -1);
        time = 0;

        for (int i = 0; i < sccCount; i++) {
            if (!visited[i]) dfsBridges(i, -1, disc, low, visited, bridges, condensedUndirected);
        }
        return bridges;
    }

    private void dfsBridges(int u, int p, int[] disc, int[] low, boolean[] visited, List<String> bridges, List<List<Integer>> graph) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        for (int v : graph.get(u)) {
            if (v == p) continue;
            if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                dfsBridges(v, u, disc, low, visited, bridges, graph);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    bridges.add(u + "<->" + v);
                }
            }
        }
    }

    private int[] getComponentMap(int n, List<List<Integer>> adj) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) if (!visited[i]) fillOrder(i, visited, stack, adj);

        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < n; i++) transpose.add(new ArrayList<>());
        for (int u = 0; u < n; u++) {
            for (int v : adj.get(u)) transpose.get(v).add(u);
        }

        int[] compMap = new int[n];
        Arrays.fill(compMap, -1);
        int sccId = 0;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (compMap[curr] == -1) {
                dfsCollect(curr, sccId++, compMap, transpose);
            }
        }
        return compMap;
    }

    private void fillOrder(int u, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
        visited[u] = true;
        for (int v : adj.get(u)) if (!visited[v]) fillOrder(v, visited, stack, adj);
        stack.push(u);
    }

    private void dfsCollect(int u, int id, int[] compMap, List<List<Integer>> transpose) {
        compMap[u] = id;
        for (int v : transpose.get(u)) if (compMap[v] == -1) dfsCollect(v, id, compMap, transpose);
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(3); adj.get(3).add(2);
        adj.get(3).add(4); adj.get(4).add(3); // SCC 0 linked to SCC 1 via single bridge path edge

        ComponentBridges cb = new ComponentBridges();
        System.out.println("Critical Component Bridges: " + cb.findComponentBridges(vertices, adj));
    }
}