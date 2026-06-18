import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * PROBLEM: Tarjan's SCC Algorithm
 * * Decompose a directed graph into its Strongly Connected Components using a single DFS pass.
 * * Strategy: Low-Link Stack Tracking
 * Maintain tracking arrays for discovery times (`disc`) and the lowest reachable discovery times (`low`). 
 * Push visited vertices onto an active LIFO stack. For each vertex, recursively process its neighbors and 
 * update its low-link value: `low[u] = min(low[u], low[v])`. 
 * If a vertex's low-link value matches its discovery time (`low[u] == disc[u]`), it is the root of an SCC. 
 * Pop vertices from the stack until you reach the root vertex to extract the complete component subgraph.
 */
public class TarjanSccAlgorithm {
    private int time = 0;
    private final List<List<Integer>> sccs = new ArrayList<>();
    private final Stack<Integer> stack = new Stack<>();

    public List<List<Integer>> runTarjan(int vertices, List<List<Integer>> adj) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] inStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            disc[i] = -1; // Initialize unvisited flags
        }

        for (int i = 0; i < vertices; i++) {
            if (disc[i] == -1) {
                dfs(i, disc, low, inStack, adj);
            }
        }
        return sccs;
    }

    private void dfs(int u, int[] disc, int[] low, boolean[] inStack, List<List<Integer>> adj) {
        disc[u] = low[u] = ++time;
        stack.push(u);
        inStack[u] = true;

        for (int v : adj.get(u)) {
            if (disc[v] == -1) {
                dfs(v, disc, low, inStack, adj);
                low[u] = Math.min(low[u], low[v]); // Update low-link from child subtree
            } else if (inStack[v]) {
                low[u] = Math.min(low[u], disc[v]); // Update low-link from back edge
            }
        }

        // If u is a root node, pop and output its strongly connected component
        if (low[u] == disc[u]) {
            List<Integer> component = new ArrayList<>();
            while (true) {
                int node = stack.pop();
                inStack[node] = false;
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

        adj.get(0).add(1); adj.get(1).add(2);
        adj.get(2).add(0); adj.get(2).add(3); // 0,1,2 form an SCC loop pointing out to 3

        TarjanSccAlgorithm tsa = new TarjanSccAlgorithm();
        System.out.println("Tarjan Extracted Components: " + tsa.runTarjan(vertices, adj)); // [[3], [2, 1, 0]]
    }
}