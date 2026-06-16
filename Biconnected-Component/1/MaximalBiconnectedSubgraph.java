import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PROBLEM: Maximal Biconnected Subgraph
 * * Identify the largest biconnected component block in a graph measured by total active vertex coverage.
 * * Strategy: Decomposition Extrema Tracker
 * Run the Hopcroft-Tarjan edge stack decomposition to extract all BCC blocks. 
 * Iterate through the components, unpacking edge pairs into a Set to track vertex coverage. 
 * Find and return the BCC block that contains the maximum number of unique vertices.
 */
public class MaximalBiconnectedSubgraph {
    public static int findMaximalBccVertexCount(int vertices, List<List<Integer>> adj) {
        BccDecomposition decomposer = new BccDecomposition();
        List<List<BccDecomposition.Edge>> bccs = decomposer.decompose(vertices, adj);

        int maxVerticesCount = 0;
        for (List<BccDecomposition.Edge> bcc : bccs) {
            Set<Integer> uniqueNodes = new HashSet<>();
            for (BccDecomposition.Edge e : bcc) {
                uniqueNodes.add(e.u);
                uniqueNodes.add(e.v);
            }
            maxVerticesCount = Math.max(maxVerticesCount, uniqueNodes.size());
        }
        return maxVerticesCount;
    }

    public static void main(String[] args) {
        System.out.println("Maximal Biconnected Subgraph tracker initialized.");
    }
}