import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * PROBLEM: Possible Bipartition
 * * We want to split a group of n people into two groups. Each person may dislike some other people, 
 * and they should not go into the same group. Given the integer n and the array dislikes, 
 * return true if it is possible to split everyone into two groups in this way.
 * * Approach:
 * Exact same concept as "Is Graph Bipartite?". 
 * Dislikes are undirected edges. We color the graph using 1 and -1 via BFS. If we ever encounter 
 * a neighbor with the same color, a valid split is impossible.
 */
public class PossibleBipartition {
    public static boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        
        for (int[] d : dislikes) {
            adj.get(d[0]).add(d[1]);
            adj.get(d[1]).add(d[0]);
        }
        
        int[] color = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            if (color[i] != 0) continue;
            
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            color[i] = 1;
            
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int enemy : adj.get(curr)) {
                    if (color[enemy] == 0) {
                        color[enemy] = -color[curr]; // Put enemy in the opposite group
                        queue.add(enemy);
                    } else if (color[enemy] == color[curr]) {
                        return false; // Found an enemy in the exact same group
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] dislikes = {{1,2}, {1,3}, {2,4}};
        System.out.println("Possible to partition? " + possibleBipartition(4, dislikes)); // true
    }
}