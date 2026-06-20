import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * PROBLEM: Bipartite Graph Check
 * * Determine if an undirected graph is bipartite, meaning its vertices can be divided into two independent 
 * sets such that no two adjacent vertices share the same set.
 * * Strategy: Breadth-First 2-Coloring Pass
 * Use an array to store colors (-1: uncolored, 0: color A, 1: color B). Traverse using BFS. 
 * For each node, color its unvisited neighbors with the opposite color. If a neighbor already shares 
 * the current node's color, the graph contains an odd cycle and is not bipartite.
 */
public class BipartiteGraphCheck {
    public static boolean isBipartite(int vertices, List<List<Integer>> adj) {
        int[] colors = new int[vertices];
        Arrays.fill(colors, -1);

        for (int i = 0; i < vertices; i++) {
            if (colors[i] == -1) {
                if (!bfsCheck(i, colors, adj)) return false;
            }
        }
        return true;
    }

    private static boolean bfsCheck(int start, int[] colors, List<List<Integer>> adj) {
        Queue<Integer> queue = new LinkedList<>();
        colors[start] = 0; // Initialize first set color partition
        queue.add(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : adj.get(curr)) {
                if (colors[neighbor] == -1) {
                    colors[neighbor] = 1 - colors[curr]; // Assign opposite structural color
                    queue.add(neighbor);
                } else if (colors[neighbor] == colors[curr]) {
                    return false; // Found adjacent node collision
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        // Square graph configuration (Bipartite)
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(3); adj.get(3).add(2);
        adj.get(3).add(0); adj.get(0).add(3);

        System.out.println("Is the target graph bipartite? " + isBipartite(vertices, adj)); // true
    }
}