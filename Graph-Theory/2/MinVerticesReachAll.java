import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Minimum Number of Vertices to Reach All Nodes
 * * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where 
 * edges[i] = [fromi, toi] represents a directed edge from fromi to toi.
 * Find the smallest set of vertices from which all nodes in the graph are reachable.
 * * Ingenious Solution Insight:
 * If a node has an incoming connection (in-degree > 0), it can always be reached from 
 * somewhere else. If a node has NO incoming edges (in-degree == 0), it is absolutely 
 * impossible to reach it from any other node. Therefore, the minimum set of nodes is 
 * exactly all nodes with an in-degree of 0.
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V) tracking array.
 */
public class MinVerticesReachAll {
    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] hasIncomingEdge = new boolean[n];
        
        for (List<Integer> edge : edges) {
            hasIncomingEdge[edge.get(1)] = true; // Mark destination node
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!hasIncomingEdge[i]) {
                result.add(i); // Node must be chosen as a entry root source
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(List.of(0, 1)); edges.add(List.of(0, 2));
        edges.add(List.of(2, 5)); edges.add(List.of(3, 4)); edges.add(List.of(4, 2));
        
        System.out.println("Minimum source entry vertices required: " + findSmallestSetOfVertices(6, edges)); // [0, 3]
    }
}