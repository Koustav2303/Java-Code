import java.util.List;

/**
 * PROBLEM: Maximal SCC Size
 * * Find the size of the single largest Strongly Connected Component block in a directed graph.
 * * Strategy: Reduction Tracker Sieve
 * Decompose the graph using Kosaraju's algorithm to separate all SCC clusters. 
 * Iterate through the components, measuring their size, and track the maximum vertex count encountered.
 * * Complexity:
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class MaximalSccSize {
    public static int findLargestSccCount(int vertices, List<List<Integer>> adj) {
        KosarajuAlgorithm decomposer = new KosarajuAlgorithm();
        List<List<Integer>> sccs = decomposer.getSCCs(vertices, adj);

        int maximumVertexSize = 0;
        for (List<Integer> bccBlock : sccs) {
            maximumVertexSize = Math.max(maximumVertexSize, bccBlock.size());
        }
        return maximumVertexSize;
    }

    public static void main(String[] args) {
        System.out.println("Maximal SCC Size metric analyzer loaded.");
    }
}