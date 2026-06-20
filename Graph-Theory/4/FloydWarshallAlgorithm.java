import java.util.Arrays;

/**
 * PROBLEM: Floyd-Warshall Algorithm
 * * Find the shortest path distances between all pairs of vertices in a weighted directed graph.
 * * Strategy: Triple-Loop Intermediate Matrix Relaxation
 * Maintain a V x V matrix. Loop through every vertex k to act as an intermediate milestone pivot point. 
 * Update path weights between all node pairs (i, j) by checking if routing through k minimizes the distance: 
 * matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k][j]).
 * * Complexity:
 * Time Complexity: O(V^3)
 * Space Complexity: O(V^2)
 */
public class FloydWarshallAlgorithm {
    private static final int INF = 100000000; // Use safe bounding integer limits to prevent overflow additions

    public static int[][] computeAllPairsShortestPaths(int vertices, int[][] graph) {
        int[][] dist = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            dist[i] = Arrays.copyOf(graph[i], vertices);
        }

        // Core dynamic programming state transitions
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 5, INF, 10},
            {INF, 0, 3, INF},
            {INF, INF, 0, 1},
            {INF, INF, INF, 0}
        };
        int[][] result = computeAllPairsShortestPaths(4, graph);
        System.out.println("Shortest path matrix coordinate (0->2): " + result[0][2]); // 8 (0->1->2)
    }
}