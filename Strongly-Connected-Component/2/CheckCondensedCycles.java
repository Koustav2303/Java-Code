import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Check Condensed Cycles
 * * Prove mathematically that condensing a directed graph into its Strongly Connected Components 
 * always yields a strict Directed Acyclic Graph (DAG) by searching the condensed meta-graph for cycles.
 * * Strategy: Topological Back-Edge Detection
 * Decompose the graph into component arrays. Construct the condensed meta-graph. 
 * Run a stateful cycle detection loop (0: unvisited, 1: processing, 2: visited) across the meta-nodes. 
 * If a processing-state node is encountered, a cycle exists—meaning the component extraction was mathematically flawed.
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */
public class CheckCondensedCycles {
    private static int time = 0;

    public static boolean hasCyclesInCondensation(int vertices, List<List<Integer>> adj) {
        // Step 1: Extract components natively using Tarjan's single-pass tracking arrays
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        int[] compMap = new int[vertices];
        boolean[] inStack = new boolean[vertices];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        java.util.Arrays.fill(disc, -1);
        java.util.Arrays.fill(compMap, -1);
        List<List<Integer>> sccs = new ArrayList<>();
        time = 0;

        for (int i = 0; i < vertices; i++) {
            if (disc[i] == -1) tarjan(i, disc, low, inStack, stack, compMap, sccs, adj);
        }

        int sccCount = sccs.size();
        List<List<Integer>> condensedGraph = new ArrayList<>();
        for (int i = 0; i < sccCount; i++) condensedGraph.add(new ArrayList<>());

        // Step 2: Build the macro adjacency tree mapping
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                if (compMap[u] != compMap[v]) {
                    condensedGraph.get(compMap[u]).add(compMap[v]);
                }
            }
        }

        // Step 3: Run standard 3-state tracking to verify the DAG property
        int[] state = new int[sccCount]; // 0=unvisited, 1=processing, 2=visited
        for (int i = 0; i < sccCount; i++) {
            if (state[i] == 0 && dfsDetectCycle(i, state, condensedGraph)) {
                return true; // Found a cycle in the condensation graph!
            }
        }
        return false;
    }

    private static void tarjan(int u, int[] disc, int[] low, boolean[] inStack, java.util.Stack<Integer> stack, int[] compMap, List<List<Integer>> sccs, List<List<Integer>> adj) {
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

    private static boolean dfsDetectCycle(int curr, int[] state, List<List<Integer>> graph) {
        state[curr] = 1; // Mark as processing
        for (int neighbor : graph.get(curr)) {
            if (state[neighbor] == 1) return true; // Back-edge detected
            if (state[neighbor] == 0 && dfsDetectCycle(neighbor, state, graph)) return true;
        }
        state[curr] = 2; // Mark as fully processed
        return false;
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(2); adj.get(2).add(0); adj.get(2).add(3);

        System.out.println("Does condensation contain cycles? " + hasCyclesInCondensation(vertices, adj)); // false
    }
}