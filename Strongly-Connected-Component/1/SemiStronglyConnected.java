import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * PROBLEM: Semi-Strongly Connected Graph
 * * Determine if a directed graph is semi-strongly connected. This means that for any pair of vertices (u, v), 
 * there exists a directed path from u to v OR from v to u.
 * * Strategy: Condensation Graph Topological Chain Check
 * Condense the graph into its SCC condensation graph. 
 * A DAG is semi-connected if and only if it contains a unique topological sort order that forms a linear chain. 
 * Compute the topological sort of the condensed meta-nodes. Loop through the sorted list and verify if a direct 
 * edge exists between every consecutive pair of components in the sequence.
 */
public class SemiStronglyConnected {
    public static boolean isSemiConnected(int vertices, List<List<Integer>> adj) {
        int[] sccCountContainer = new int[1];
        List<Set<Integer>> condensedDAG = CondensationGraph.buildCondensationGraph(vertices, adj, sccCountContainer);
        int sccCount = sccCountContainer[0];

        if (sccCount <= 1) return true; // Graph is already strongly connected, and thus semi-connected

        // Compute the topological sort of the condensed components
        Stack<Integer> topoStack = new Stack<>();
        boolean[] visited = new boolean[sccCount];
        for (int i = 0; i < sccCount; i++) {
            if (!visited[i]) dfsTopo(i, visited, topoStack, condensedDAG);
        }

        int currComponent = topoStack.pop();
        while (!topoStack.isEmpty()) {
            int nextComponent = topoStack.pop();
            // Verify if a direct edge connection links consecutive components in the chain
            if (!condensedDAG.get(currComponent).contains(nextComponent)) {
                return false; // Found a disconnect in the topological chain path
            }
            currComponent = nextComponent;
        }
        return true;
    }

    private static void dfsTopo(int u, boolean[] visited, Stack<Integer> stack, List<Set<Integer>> condensedDAG) {
        visited[u] = true;
        for (int v : condensedDAG.get(u)) {
            if (!visited[v]) dfsTopo(v, visited, stack, condensedDAG);
        }
        stack.push(u);
    }

    public static void main(String[] args) {
        System.out.println("Semi-Strong connectivity topological parser online.");
    }
}