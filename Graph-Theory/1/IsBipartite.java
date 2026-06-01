import java.util.*;

public class IsBipartite {
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; 
        
        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) continue; 
            
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            colors[i] = 1;
            
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int neighbor : graph[node]) {
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = -colors[node];
                        queue.add(neighbor);
                    } else if (colors[neighbor] == colors[node]) {
                        return false; 
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        System.out.println("Is graph bipartite? " + isBipartite(graph)); // false
    }
}