import java.util.List;
import java.util.Set;

/**
 * PROBLEM: Path Between Components
 * * Given a source vertex u and a target vertex v, determine if a directed path exists from 
 * the SCC containing u to the SCC containing v.
 * * Strategy: Meta-Graph Traversal Scan
 * Condense the graph into its SCC condensation graph. 
 * Identify the component IDs for both query vertices, then run a standard reachability search (DFS/BFS) 
 * on the condensed DAG from the source component to the target component.
 */
public class PathBetweenComponents {
    public static boolean isPathPossible(int vertices, List<List<Integer>> adj, int[] componentMap, int sccCount, List<Set<Integer>> condensedDAG, int u, int v) {
        int startComponent = componentMap[u];
        int targetComponent = componentMap[v];

        boolean[] visitedComponents = new boolean[sccCount];
        return dfsComponentReach(startComponent, targetComponent, visitedComponents, condensedDAG);
    }

    private static boolean dfsComponentReach(int curr, int target, boolean[] visited, List<Set<Integer>> condensedDAG) {
        if (curr == target) return true;
        visited[curr] = true;

        for (int nextComponent : condensedDAG.get(curr)) {
            if (!visited[nextComponent]) {
                if (dfsComponentReach(nextComponent, target, visited, condensedDAG)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Condensed Component reachability module loaded.");
    }
}