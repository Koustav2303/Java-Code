import java.util.*;

/**
 * PROBLEM: Find Eventual Safe States
 * * There is a directed graph of n nodes with each node labeled from 0 to n - 1.
 * A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible 
 * path starting from that node leads to a terminal node (or another safe node).
 * Given a 2D integer array graph, return an array containing all the safe nodes of the graph in ascending order.
 * * Strategy:
 * Use a state-array for cycle detection via DFS. 
 * States: 0 = unvisited, 1 = visiting (in current recursion path), 2 = completely processed (safe).
 * Any node caught in a cycle or leading directly to a cycle cannot be safe.
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class FindEventualSafeStates {
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] state = new int[n]; // 0: unvisited, 1: visiting, 2: safe
        List<Integer> safeNodes = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (dfs(i, graph, state)) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }
    
    private static boolean dfs(int node, int[][] graph, int[] state) {
        if (state[node] > 0) {
            return state[node] == 2; // Safe if already processed completely and verified
        }
        
        state[node] = 1; // Mark as visiting
        for (int neighbor : graph[node]) {
            if (state[neighbor] == 1 || !dfs(neighbor, graph, state)) {
                return false; // Found a cycle or path to an unsafe state
            }
        }
        
        state[node] = 2; // Mark as verified safe
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
        System.out.println("Eventual safe nodes: " + eventualSafeNodes(graph)); // [2, 4, 5, 6]
    }
}