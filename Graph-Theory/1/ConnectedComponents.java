import java.util.*;

public class ConnectedComponents {
    public static int countComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        int components = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                dfs(adj, visited, i);
            }
        }
        return components;
    }
    
    private static void dfs(List<List<Integer>> adj, boolean[] visited, int node) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) dfs(adj, visited, neighbor);
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println("Connected components: " + countComponents(5, edges)); // 2
    }
}