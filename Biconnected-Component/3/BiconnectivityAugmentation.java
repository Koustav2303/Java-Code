import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PROBLEM: BiconnectivityAugmentation
 * * Find the minimum number of edges that must be added to a connected undirected graph 
 * to make the entire graph fully biconnected (immune to single vertex failures).
 * * Strategy: Block-Cut Tree Leaf Extraction
 * Condense the graph components into a bipartite Block-Cut Tree framework. 
 * Identify all "leaf blocks" (BCC components that contain exactly one articulation point). 
 * If $L$ represents the total count of leaf blocks, the minimum number of edges needed 
 * to biconnect the entire graph structure is given by: $\lceil L / 2 \rceil$.
 */
public class BiconnectivityAugmentation {
    public static int minEdgesToBiconnect(int vertices, List<List<BccDiameter.Edge>> bccs, List<Integer> aps) {
        if (bccs.size() <= 1) return 0;

        int leafBlocksCount = 0;
        for (List<BccDiameter.Edge> bcc : bccs) {
            Set<Integer> uniqueNodes = new HashSet<>();
            for (BccDiameter.Edge e : bcc) {
                uniqueNodes.add(e.u);
                uniqueNodes.add(e.v);
            }

            int apCountInBlock = 0;
            for (int node : uniqueNodes) {
                if (aps.contains(node)) apCountInBlock++;
            }

            // A block with exactly 1 articulation point acts as a leaf node inside the Block-Cut Tree
            if (apCountInBlock == 1) {
                leafBlocksCount++;
            }
        }

        return (leafBlocksCount + 1) / 2; // Implements ceiling(L / 2) formula structure
    }

    public static void main(String[] args) {
        System.out.println("Biconnectivity Augmentation tracking optimization framework configured.");
    }
}