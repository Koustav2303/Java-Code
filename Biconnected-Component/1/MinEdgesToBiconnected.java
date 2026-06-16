import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Minimum Edges to Biconnect
 * * Find the minimum number of edges that must be added to an undirected graph 
 * to make it fully 2-edge-connected (immune to any single edge failure).
 * * Strategy: Leaf Node Formula Strategy
 * Compress all 2-edge-connected components into a condensed tree using the bridge-block strategy. 
 * The tree nodes are the components, and the tree edges are the original bridges. 
 * Count the number of leaf nodes (nodes with degree == 1) in this condensed tree. 
 * The minimum number of edges needed to 2-edge-connect the graph is given by: (leaves + 1) / 2.
 */
public class MinEdgesToBiconnected { // Class matches filename perfectly
    public static int minEdgesTo2EdgeConnect(int n, List<List<Integer>> adj, List<List<Integer>> components) {
        if (components.size() <= 1) return 0;

        int[] componentIdMap = new int[n];
        for (int compId = 0; compId < components.size(); compId++) { // Fixed: changed bccId to compId
            for (int node : components.get(compId)) {
                componentIdMap[node] = compId;
            }
        }

        int[] condensedDegrees = new int[components.size()];
        // Compute structural degree constraints across condensed meta-nodes
        for (int u = 0; u < n; u++) {
            for (int v : adj.get(u)) {
                if (componentIdMap[u] != componentIdMap[v]) {
                    condensedDegrees[componentIdMap[u]]++;
                }
            }
        }

        int leafNodesCount = 0;
        for (int deg : condensedDegrees) {
            if (deg == 1) leafNodesCount++;
        }

        return (leafNodesCount + 1) / 2; // (L + 1) / 2 formula implementation
    }

    public static void main(String[] args) {
        System.out.println("Edges adding optimizer algorithm compiled successfully!");
    }
}