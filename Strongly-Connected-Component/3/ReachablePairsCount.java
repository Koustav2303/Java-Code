import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * PROBLEM: Reachable Pairs Count
 * * Count the total number of ordered vertex pairs (u, v) where v is reachable from u.
 * * Strategy: Monolithic Condensed DAG BitSet Or-Aggregation
 * Eliminates external lookup linkages by performing bitwise calculations locally.
 */
public class ReachablePairsCount {
    private static int time = 0;

    public static long countReachablePairs(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        int[] compMap = new int[vertices];
        boolean[] inStack = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        
        Arrays.fill(disc, -1);
        Arrays.fill(compMap, -1);
        List<List<Integer>> sccs = new ArrayList<>();
        time = 0;

        // Step 1: Decompose graph into component IDs
        for (int i = 0; i < vertices; i++) {
            if (disc[i] == -1) {
                tarjan(i, disc, low, inStack, stack, compMap, sccs, adj);
            }
        }

        int sccCount = sccs.size();
        List<Set<Integer>> condensedDAG = new ArrayList<>();
        for (int i = 0; i < sccCount; i++) condensedDAG.add(new HashSet<>());

        // Step 2: Build the unique macro-adjacency list maps
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                if (compMap[u] != compMap[v]) {
                    condensedDAG.get(compMap[u]).add(compMap[v]);
                }
            }
        }

        // Step 3: Compute topological sort of the condensed components
        Stack<Integer> topoStack = new Stack<>();
        boolean[] visited = new boolean[sccCount];
        for (int i = 0; i < sccCount; i++) {
            if (!visited[i]) dfsTopo(i, visited, topoStack, condensedDAG);
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!topoStack.isEmpty()) topoOrder.add(topoStack.pop());

        BitSet[] reach = new BitSet[sccCount];
        for (int i = 0; i < sccCount; i++) {
            reach[i] = new BitSet(sccCount);
            reach[i].set(i);
        }

        // Step 4: Process in reverse topological order via bitwise OR aggregations
        for (int i = topoOrder.size() - 1; i >= 0; i--) {
            int u = topoOrder.get(i);
            for (int v : condensedDAG.get(u)) {
                reach[u].or(reach[v]);
            }
        }

        // Step 5: Compute final reachable permutations based on cross-component metrics
        long totalPairsCount = 0;
        for (int i = 0; i < sccCount; i++) {
            long sizeU = sccs.get(i).size();
            for (int j = reach[i].nextSetBit(0); j >= 0; j = reach[i].nextSetBit(j + 1)) {
                long sizeV = sccs.get(j).size();
                totalPairsCount += sizeU * sizeV;
            }
        }
        return totalPairsCount;
    }

    private static void dfsTopo(int u, boolean[] visited, Stack<Integer> stack, List<Set<Integer>> graph) {
        visited[u] = true;
        for (int v : graph.get(u)) {
            if (!visited[v]) dfsTopo(v, visited, stack, graph);
        }
        stack.push(u);
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
            List<Integer> comp = new ArrayList<>();
            int id = sccs.size();
            while (true) {
                int node = stack.pop();
                inStack[node] = false;
                compMap[node] = id;
                comp.add(node);
                if (node == u) break;
            }
            sccs.add(comp);
        }
    }

    public static void main(String[] args) {
        int vertices = 3;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(2);

        System.out.println("Total Reachable Vertex Pairs count: " + countReachablePairs(vertices, adj)); // 6
    }
}