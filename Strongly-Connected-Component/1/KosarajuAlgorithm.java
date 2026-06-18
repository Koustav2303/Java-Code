import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * PROBLEM: Kosaraju's Algorithm
 * * Decompose an arbitrary directed graph into its constituent Strongly Connected Components.
 * * Strategy: Topological Finishing Sort & Transpose Collection
 * 1. Run a DFS pass over the graph to push vertices onto a LIFO stack based on their sub-tree completion order.
 * 2. Transpose the graph (invert all directed edge links).
 * 3. Pop elements from the stack sequentially. If a vertex is unvisited, it forms the root of a new SCC. 
 * Run a secondary DFS on the transposed graph to collect all reachable vertices belonging to this component.
 */
public class KosarajuAlgorithm {
    private void fillOrder(int u, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) fillOrder(v, visited, stack, adj);
        }
        stack.push(u);
    }

    private void dfsTranspose(int u, boolean[] visited, List<Integer> component, List<List<Integer>> transpose) {
        visited[u] = true;
        component.add(u);
        for (int v : transpose.get(u)) {
            if (!visited[v]) dfsTranspose(v, visited, component, transpose);
        }
    }

    public List<List<Integer>> getSCCs(int vertices, List<List<Integer>> adj) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];

        // Pass 1: Build the processing order based on sub-tree completion times
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) fillOrder(i, visited, stack, adj);
        }

        // Generate the transposed graph
        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < vertices; i++) transpose.add(new ArrayList<>());
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) transpose.get(v).add(u);
        }

        List<List<Integer>> sccsList = new ArrayList<>();
        boolean[] visitedTranspose = new boolean[vertices];

        // Pass 2: Pop elements from the stack to extract components in topological order
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!visitedTranspose[curr]) {
                List<Integer> component = new ArrayList<>();
                dfsTranspose(curr, visitedTranspose, component, transpose);
                sccsList.add(component);
            }
        }
        return sccsList;
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());

        adj.get(1).add(0); adj.get(0).add(2);
        adj.get(2).add(1); adj.get(0).add(3); adj.get(3).add(4);

        KosarajuAlgorithm ka = new KosarajuAlgorithm();
        System.out.println("Strongly Connected Components: " + ka.getSCCs(vertices, adj));
    }
}