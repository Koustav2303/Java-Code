import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * PROBLEM: Condensed Graph Diameter
 * * Find the longest path length (measured by the number of unique components crossed) 
 * inside the condensation Directed Acyclic Graph (DAG) of a directed graph.
 * * Strategy: Topological Longest Path DP
 * Decompose the graph into its SCC components. Construct the condensed DAG. 
 * Compute a valid topological sorting of the meta-nodes. Run a dynamic programming array pass 
 * over the sorted sequence to accumulate the maximum downstream transition paths.
 */
public class CondensedGraphDiameter {
    private int time = 0;

    public int getCondensationDiameter(int vertices, List<List<Integer>> adj) {
        int[] compMap = new int[vertices];
        List<List<Integer>> sccs = runTarjan(vertices, compMap, adj);
        int sccCount = sccs.size();

        List<List<Integer>> condensedGraph = new ArrayList<>();
        for (int i = 0; i < sccCount; i++) condensedGraph.add(new ArrayList<>());

        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                if (compMap[u] != compMap[v]) {
                    condensedGraph.get(compMap[u]).add(compMap[v]);
                }
            }
        }

        // Compute Topological sort of the condensed graph components
        Stack<Integer> topoStack = new Stack<>();
        boolean[] visited = new boolean[sccCount];
        for (int i = 0; i < sccCount; i++) {
            if (!visited[i]) dfsTopo(i, visited, topoStack, condensedGraph);
        }

        int[] dp = new int[sccCount];
        int maxDiameter = 0;

        while (!topoStack.isEmpty()) {
            int u = topoStack.pop();
            maxDiameter = Math.max(maxDiameter, dp[u]);
            for (int v : condensedGraph.get(u)) {
                dp[v] = Math.max(dp[v], dp[u] + 1);
            }
        }
        return maxDiameter;
    }

    private void dfsTopo(int u, boolean[] visited, Stack<Integer> stack, List<List<Integer>> graph) {
        visited[u] = true;
        for (int v : graph.get(u)) if (!visited[v]) dfsTopo(v, visited, stack, graph);
        stack.push(u);
    }

    private List<List<Integer>> runTarjan(int n, int[] compMap, List<List<Integer>> adj) {
        int[] disc = new int[n]; int[] low = new int[n]; boolean[] inStack = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(disc, -1); Arrays.fill(compMap, -1);
        List<List<Integer>> sccs = new ArrayList<>();
        time = 0;

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) tarjanDfs(i, disc, low, inStack, stack, compMap, sccs, adj);
        }
        return sccs;
    }

    private void tarjanDfs(int u, int[] disc, int[] low, boolean[] inStack, Stack<Integer> stack, int[] compMap, List<List<Integer>> sccs, List<List<Integer>> adj) {
        disc[u] = low[u] = ++time; stack.push(u); inStack[u] = true;
        for (int v : adj.get(u)) {
            if (disc[v] == -1) {
                tarjanDfs(v, disc, low, inStack, stack, compMap, sccs, adj);
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if (low[u] == disc[u]) {
            List<Integer> comp = new ArrayList<>();
            int id = sccs.size();
            while (true) {
                int node = stack.pop(); inStack[node] = false;
                compMap[node] = id; comp.add(node);
                if (node == u) break;
            }
            sccs.add(comp);
        }
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(2); adj.get(2).add(3); // Linear path chain DAG configuration

        CondensedGraphDiameter cgd = new CondensedGraphDiameter();
        System.out.println("Condensation DAG Diameter Max Path length: " + cgd.getCondensationDiameter(vertices, adj)); // 3
    }
}