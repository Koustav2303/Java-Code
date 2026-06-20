import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * PROBLEM: Topological Sort
 * * Compute a valid linear topological ordering sequence for a Directed Acyclic Graph (DAG).
 * * Strategy: Kahn's In-Degree Queue Sieve
 * Calculate the in-degrees of all vertices. Push vertices with an in-degree of 0 into a Queue. 
 * Pop a vertex, add it to your topological sort output list, and decrement the in-degrees of its neighbors. 
 * If a neighbor's in-degree drops to 0, push it into the queue. 
 * If the output list size does not equal V at the end, the graph contains a cycle.
 */
public class TopologicalSortKahn {
    public static List<Integer> computeTopologicalOrder(int vertices, List<List<Integer>> adj) {
        int[] inDegree = new int[vertices];
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order.add(curr);

            for (int neighbor : adj.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) queue.add(neighbor);
            }
        }

        if (order.size() != vertices) return new ArrayList<>(); // Graph possesses cyclic structural dependencies
        return order;
    }

    public static void main(String[] args) {
        int vertices = 3;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(2); // Direct linear dependency chain graph (0 -> 1 -> 2)

        System.out.println("Valid topological kahn sorting sequence order: " + computeTopologicalOrder(vertices, adj)); // [0, 1, 2]
    }
}