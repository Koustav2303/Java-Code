import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Largest Cycle Within SCC
 * * Find the length of the maximum simple cycle restricted entirely within a single Strongly Connected Component.
 * * Strategy: Bounded Recursive Backtracking
 * Because finding the longest simple cycle is NP-Hard, isolate the vertices belonging to the target component 
 * and run a localized depth-first search with backtracking. Track path depths and update the maximum length 
 * whenever a back-edge connects to the path origin point.
 */
public class LargestCycleWithinScc {
    private int maxCycleLen = 0;

    public int findLongestCycle(List<Integer> sccNodes, List<List<Integer>> adj) {
        boolean[] isMember = new boolean[adj.size()];
        for (int node : sccNodes) isMember[node] = true;

        for (int startNode : sccNodes) {
            boolean[] visited = new boolean[adj.size()];
            dfs(startNode, startNode, 0, visited, isMember, adj);
        }
        return maxCycleLen;
    }

    private void dfs(int curr, int target, int depth, boolean[] visited, boolean[] isMember, List<List<Integer>> adj) {
        visited[curr] = true;
        for (int neighbor : adj.get(curr)) {
            if (!isMember[neighbor]) continue;
            
            if (neighbor == target) {
                maxCycleLen = Math.max(maxCycleLen, depth + 1);
            } else if (!visited[neighbor]) {
                dfs(neighbor, target, depth + 1, visited, isMember, adj);
            }
        }
        visited[curr] = false; // Backtrack state unwinding
    }

    public static void main(String[] args) {
        System.out.println("Longest internal simple cycle processing framework ready.");
    }
}