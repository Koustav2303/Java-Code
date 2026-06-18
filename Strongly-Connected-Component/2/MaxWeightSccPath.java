import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Max Weight SCC Path
 * * Given a directed graph where every vertex has an associated weight value, find the maximum 
 * total weight path possible. You can move freely through nodes and along directed edges.
 * * Strategy: Condensed DAG Dynamic Programming
 * Moving into any node of an SCC allows you to visit all other nodes in that component for free. 
 * First, compress the graph into its SCC condensation graph, where each meta-node's weight is the sum 
 * of the weights of all vertices in that component. Since the condensation graph is a DAG, 
 * find the longest path using topological sorting and dynamic programming.
 */
public class MaxWeightSccPath {
    private static int time = 0;

    public static int getMaxPathWeight(int vertices, int[] nodeWeights, List<List<Integer>> adj) {
        // Condense the graph into an SCC meta-graph using Tarjan's algorithm
        int[] disc = new int[vertices]; int[] low = new int[vertices]; int[] compMap = new int[vertices];
        boolean[] inStack = new boolean[vertices]; java.util.Stack<Integer> stack = new java.util.Stack<>();
        java.util.Arrays.fill(disc, -1);
        List<List<Integer>> sccs = new ArrayList<>();
        time = 0;

        for (int i = 0; i < vertices; i++) {
            if (disc[i] == -1) tarjan(i, disc, low, inStack, stack, compMap, sccs, adj);
        }

        int sccCount = sccs.size();
        int[] sccWeights = new int[sccCount];
        for (int i = 0; i < vertices; i++) {
            sccWeights[compMap[i]] += nodeWeights[i];
        }

        List<List<Integer>> condensedGraph = new ArrayList<>();
        int[] inDegree = new int[sccCount];
        for (int i = 0; i < sccCount; i++) condensedGraph.add(new ArrayList<>());

        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                if (compMap[u] != compMap[v]) {
                    condensedGraph.get(compMap[u]).add(compMap[v]);
                    inDegree[compMap[v]]++;
                }
            }
        }

        // Run dynamic programming across the condensed DAG structures
        int[] dp = new int[sccCount];
        java.util.Queue<Integer> q = new java.util.LinkedList<>();
        for (int i = 0; i < sccCount; i++) {
            dp[i] = sccWeights[i];
            if (inDegree[i] == 0) q.add(i);
        }

        int maxGlobalWeight = 0;
        while (!pqIsEmpty(q)) {
            int curr = q.poll();
            maxGlobalWeight = Math.max(maxGlobalWeight, dp[curr]);

            for (int neighbor : condensedGraph.get(curr)) {
                dp[neighbor] = Math.max(dp[neighbor], dp[curr] + sccWeights[neighbor]);
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) q.add(neighbor);
            }
        }
        return maxGlobalWeight;
    }

    private static boolean pqIsEmpty(java.util.Queue<Integer> q) { return q.isEmpty(); }

    private static void tarjan(int u, int[] disc, int[] low, boolean[] inStack, java.util.Stack<Integer> stack, int[] compMap, List<List<Integer>> sccs, List<List<Integer>> adj) {
        disc[u] = low[u] = ++time; stack.push(u); inStack[u] = true;
        for (int v : adj.get(u)) {
            if (disc[v] == -1) {
                tarjan(v, disc, low, inStack, stack, compMap, sccs, adj);
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if (low[u] == disc[u]) {
            List<Integer> component = new ArrayList<>();
            int componentId = sccs.size();
            while (true) {
                int node = stack.pop(); inStack[node] = false;
                compMap[node] = componentId; component.add(node);
                if (node == u) break;
            }
            sccs.add(component);
        }
    }

    public static void main(String[] args) {
        int[] weights = {10, 20, 5, 40};
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0); adj.get(1).add(2); adj.get(2).add(3);

        System.out.println("Maximum achievable path weight: " + getMaxPathWeight(4, weights, adj)); // 75
    }
}