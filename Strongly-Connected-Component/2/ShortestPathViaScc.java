import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Shortest Path Via SCC
 * * Find the shortest path between a source node $u$ and a target node $v$ under a specific cost model: 
 * traveling along edges within the same SCC costs 0, while crossing between different SCCs costs 1.
 * * Strategy: 0-1 Deque Decompression
 * Decompose the graph to create a component mapping array. 
 * Run a 0-1 BFS using a double-ended queue (Deque). When evaluating an edge $(u, v)$:
 * - If `comp[u] == comp[v]`, the edge weight is 0; push $v$ to the *front* of the deque.
 * - If `comp[u] != comp[v]`, the edge weight is 1; push $v$ to the *back* of the deque.
 */
public class ShortestPathViaScc {
    public static int findMinSccTransits(int vertices, int[] compMap, List<List<Integer>> adj, int src, int dest) {
        int[] dist = new int[vertices];
        java.util.Arrays.fill(dist, Integer.MAX_VALUE);
        java.util.Deque<Integer> deque = new java.util.LinkedList<>();

        dist[src] = 0;
        deque.add(src);

        while (!deque.isEmpty()) {
            int u = deque.pollFirst();
            if (u == dest) return dist[dest];

            for (int v : adj.get(u)) {
                int costWeightValue = (compMap[u] == compMap[v]) ? 0 : 1;
                if (dist[u] + costWeightValue < dist[v]) {
                    dist[v] = dist[u] + costWeightValue;
                    if (costWeightValue == 0) {
                        deque.addFirst(v); // Prioritize zero-cost components
                    } else {
                        deque.addLast(v);
                    }
                }
            }
        }
        return dist[dest] == Integer.MAX_VALUE ? -1 : dist[dest];
    }

    public static void main(String[] args) {
        System.out.println("0-1 Deque macro-transit router compiled successfully.");
    }
}