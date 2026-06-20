import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROBLEM: Bellman-Ford Algorithm
 * * Given a weighted directed graph with vertices and edges, find the shortest distance from a source vertex 
 * to all other vertices. The graph can contain negative edge weights and negative cycles.
 * * Strategy: Edge Relaxation Iteration
 * Initialize distances to infinity and source to 0. Relax all edges E exactly V - 1 times. 
 * On the V-th pass, if any distance can still be minimized, it proves the existence of a negative cycle.
 * * Complexity:
 * Time Complexity: O(V * E)
 * Space Complexity: O(V)
 */
public class BellmanFordAlgorithm {
    static class Edge {
        int src, dest, weight;
        Edge(int s, int d, int w) { this.src = s; this.dest = d; this.weight = w; }
    }

    public static boolean getShortestPaths(int vertices, List<Edge> edges, int source, int[] dist) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Relax all edges V - 1 times
        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        // V-th iteration pass to catch negative weight cycles
        for (Edge edge : edges) {
            if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                return false; // Negative cycle detected!
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(1, 2, -2));
        edges.add(new Edge(2, 3, 3));
        edges.add(new Edge(3, 1, -2)); // Creates a negative cycle loop (1 -> 2 -> 3 -> 1 sum = -1)

        int[] dist = new int[vertices];
        boolean success = getShortestPaths(vertices, edges, 0, dist);
        System.out.println("Does graph lack negative cycles? " + success); // false
    }
}