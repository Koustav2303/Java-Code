import java.util.LinkedList;
import java.util.Queue;

public class IsGraphBipartite {
    public static boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] != 0) continue; // Already colored
            
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            colors[i] = 1; // Start coloring with color '1'
            
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int next : graph[curr]) {
                    if (colors[next] == 0) { // If uncolored, color it oppositely
                        colors[next] = -colors[curr];
                        queue.add(next);
                    } else if (colors[next] == colors[curr]) {
                        return false; // Found an adjacent node with the SAME color
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {{1,2,3}, {0,2}, {0,1,3}, {0,2}};
        System.out.println("Is graph bipartite? " + isBipartite(graph));
    }
}