import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: SCC Reachability Count
 * * Compute the total number of individual vertices that can be reached starting from a specific component 
 * in the condensation graph.
 * * Strategy: Condensed Sub-Tree Node Accumulation
 * Construct the condensed DAG. To find the reachability footprint of a component, run a DFS 
 * on the condensed DAG starting from its component ID. Sum the sizes of all reachable components, 
 * ensuring each component is counted at most once.
 */
public class SccReachabilityCount {
    public static int calculateReachabilityFootprint(int targetSccId, List<List<Integer>> sccs, List<List<Integer>> condensedGraph) {
        boolean[] visitedSccs = new boolean[condensedGraph.size()];
        return dfsAccumulate(targetSccId, visitedSccs, sccs, condensedGraph);
    }

    private static int dfsAccumulate(int currScc, boolean[] visited, List<List<Integer>> sccs, List<List<Integer>> condensedGraph) {
        visited[currScc] = true;
        int currentComponentSizeCount = sccs.get(currScc).size();

        for (int neighborScc : condensedGraph.get(currScc)) {
            if (!visited[neighborScc]) {
                currentComponentSizeCount += dfsAccumulate(neighborScc, visited, sccs, condensedGraph);
            }
        }
        return currentComponentSizeCount;
    }

    public static void main(String[] args) {
        System.out.println("Macro sub-tree component reachability counter loaded.");
    }
}