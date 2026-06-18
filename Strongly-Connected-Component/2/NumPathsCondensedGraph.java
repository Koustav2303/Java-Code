import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Number of Paths in Condensed Graph
 * * Given a source vertex u and a target vertex v, count the number of unique paths from the component 
 * containing u to the component containing v in the condensation graph.
 * * Strategy: Memoized DAG Path Summing
 * First, identify the component IDs for all vertices. Construct the condensed DAG. 
 * Calculate path counts between the two target component IDs using a memoized DFS post-order traversal, 
 * summing the paths of downstream neighbors.
 */
public class NumPathsCondensedGraph {
    public static int countPathsBetweenComponents(int sccCount, List<List<Integer>> condensedDAG, int startScc, int targetScc) {
        int[] memo = new int[sccCount];
        java.util.Arrays.fill(memo, -1);
        return dfsCount(startScc, targetScc, memo, condensedDAG);
    }

    private static int dfsCount(int curr, int target, int[] memo, List<List<Integer>> condensedDAG) {
        if (curr == target) return 1;
        if (memo[curr] != -1) return memo[curr];

        int totalPaths = 0;
        for (int neighbor : condensedDAG.get(curr)) {
            totalPaths += dfsCount(neighbor, target, memo, condensedDAG);
        }
        
        return memo[curr] = totalPaths;
    }

    public static void main(String[] args) {
        System.out.println("Condensed graph path aggregator online.");
    }
}