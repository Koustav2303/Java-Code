import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * PROBLEM: Condensation Graph Constructor
 * * Condense a directed graph into a Directed Acyclic Graph (DAG) where every meta-node 
 * represents an individual Strongly Connected Component (SCC).
 * * Strategy: Meta-ID Subgraph Splicing
 * Run Kosaraju's algorithm to identify and index all SCC blocks. Assign each vertex to its respective SCC ID. 
 * Iterate through the original edge pairs; if the source and target endpoints belong to different components, 
 * insert a meta-edge between their component IDs inside a new condensed adjacency list.
 */
public class CondensationGraph {
    private static void fillOrder(int u, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) fillOrder(v, visited, stack, adj);
        }
        stack.push(u);
    }

    private static void dfsComponent(int u, int componentId, int[] componentMap, List<List<Integer>> transpose) {
        componentMap[u] = componentId;
        for (int v : transpose.get(u)) {
            if (componentMap[v] == -1) dfsComponent(v, componentId, componentMap, transpose);
        }
    }

    public static List<Set<Integer>> buildCondensationGraph(int vertices, List<List<Integer>> adj, int[] totalComponents) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) fillOrder(i, visited, stack, adj);
        }

        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < vertices; i++) transpose.add(new ArrayList<>());
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) transpose.get(v).add(u);
        }

        int[] componentMap = new int[vertices];
        for (int i = 0; i < vertices; i++) componentMap[i] = -1;

        int sccCount = 0;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (componentMap[curr] == -1) {
                dfsComponent(curr, sccCount, componentMap, transpose);
                sccCount++;
            }
        }
        totalComponents[0] = sccCount;

        // Build the condensed Directed Acyclic Graph layout using Sets to handle duplicates
        List<Set<Integer>> condensedAdj = new ArrayList<>();
        for (int i = 0; i < sccCount; i++) condensedAdj.add(new HashSet<>());

        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                int sccU = componentMap[u];
                int sccV = componentMap[v];
                if (sccU != sccV) {
                    condensedAdj.get(sccU).add(sccV);
                }
            }
        }
        return condensedAdj;
    }

    public static void main(String[] args) {
        System.out.println("Condensation Graph construction engine ready.");
    }
}