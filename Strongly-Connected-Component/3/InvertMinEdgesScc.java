import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * PROBLEM: Minimum Edge Inversions for Global SCC
 * * Find the minimum number of directed edges that must be inverted to transform 
 * a directed graph into a single strongly connected component.
 * * Strategy: Natively Embedded Tarjan Condensation Invariant
 * Dynamically breaks dependencies by calculating components in-place.
 */
public class InvertMinEdgesScc {
    private static int time = 0;

    public static int minReversalsForScc(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        int[] compMap = new int[vertices];
        boolean[] inStack = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        
        Arrays.fill(disc, -1);
        Arrays.fill(compMap, -1);
        List<List<Integer>> sccs = new ArrayList<>();
        time = 0;

        // Step 1: Compute SCCs using local Tarjan pass
        for (int i = 0; i < vertices; i++) {
            if (disc[i] == -1) {
                tarjan(i, disc, low, inStack, stack, compMap, sccs, adj);
            }
        }

        int sccCount = sccs.size();
        if (sccCount <= 1) return 0; // Graph is already strongly connected

        int[] inDegree = new int[sccCount];
        int[] outDegree = new int[sccCount];

        // Step 2: Compute degrees across component boundaries
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                if (compMap[u] != compMap[v]) {
                    outDegree[compMap[u]]++;
                    inDegree[compMap[v]]++;
                }
            }
        }

        // Step 3: Count isolated source and sink meta-nodes
        int sources = 0, sinks = 0;
        for (int i = 0; i < sccCount; i++) {
            if (inDegree[i] == 0) sources++;
            if (outDegree[i] == 0) sinks++;
        }

        return Math.max(sources, sinks);
    }

    private static void tarjan(int u, int[] disc, int[] low, boolean[] inStack, Stack<Integer> stack, int[] compMap, List<List<Integer>> sccs, List<List<Integer>> adj) {
        disc[u] = low[u] = ++time;
        stack.push(u);
        inStack[u] = true;

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
                int node = stack.pop();
                inStack[node] = false;
                compMap[node] = componentId;
                component.add(node);
                if (node == u) break;
            }
            sccs.add(component);
        }
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(2); adj.get(2).add(3);

        System.out.println("Minimum edge inversions needed: " + minReversalsForScc(vertices, adj)); // 1
    }
}