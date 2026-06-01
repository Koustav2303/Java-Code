import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: All Paths From Source to Target
 * * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, 
 * find all possible paths from node 0 to node n - 1 and return them in any order.
 * * Strategy:
 * Backtracking on the graph. Since the graph is guaranteed to be a DAG, we do not 
 * need a visited array to prevent infinite cycles.
 * * Complexity:
 * Time Complexity: O(2^N * N) worst case scenario combinations.
 * Space Complexity: O(N) path tracking memory depth.
 */
public class AllPathsSourceTarget {
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(0); // Start at source node 0
        dfs(0, graph, currentPath, paths);
        return paths;
    }
    
    private static void dfs(int node, int[][] graph, List<Integer> currentPath, List<List<Integer>> paths) {
        if (node == graph.length - 1) {
            paths.add(new ArrayList<>(currentPath)); // Deep copy match
            return;
        }
        
        for (int neighbor : graph[node]) {
            currentPath.add(neighbor);
            dfs(neighbor, graph, currentPath, paths);
            currentPath.remove(currentPath.size() - 1); // Backtrack step
        }
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        System.out.println("All paths from 0 to 3: " + allPathsSourceTarget(graph));
    }
}