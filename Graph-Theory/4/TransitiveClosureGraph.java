import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Transitive Closure
 * * Given a directed graph, compute its reachability matrix closure where position index matrix[i][j] 
 * is exactly 1 if a directed path exists from node i to node j, and 0 otherwise.
 * * Strategy: Vertex DFS Reachability pass
 * Initialize a V x V matrix with 0s. Run a standard DFS pass starting from each individual node i. 
 * For every node j reached during node i's DFS pass, set matrix[i][j] to 1.
 * * Complexity:
 * Time Complexity: O(V * (V + E))
 * Space Complexity: O(V^2)
 */
public class TransitiveClosureGraph {
    public static int[][] computeTransitiveClosure(int vertices, List<List<Integer>> adj) {
        int[][] reachabilityMatrix = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            dfsTrace(i, i, reachabilityMatrix, adj);
        }
        return reachabilityMatrix;
    }

    private static void dfsTrace(int start, int curr, int[][] matrix, List<List<Integer>> adj) {
        matrix[start][curr] = 1; // Mark reachability from start node anchor point to current target
        for (int neighbor : adj.get(curr)) {
            if (matrix[start][neighbor] == 0) {
                dfsTrace(start, neighbor, matrix, adj);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 3;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(2); // 0 can reach 1 and 2, 1 can reach 2, 2 can reach itself

        int[][] closure = computeTransitiveClosure(vertices, adj);
        System.out.println("Can node 0 reach node 2? " + (closure[0][2] == 1)); // true
    }
}