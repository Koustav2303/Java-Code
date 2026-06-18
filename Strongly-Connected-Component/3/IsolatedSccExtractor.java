import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Isolated SCC Subgraph Extractor
 * * Isolate a specific Strongly Connected Component block by its ID and extract its internal sub-graph structure. 
 * This means filtering out all edges whose endpoints do not both reside within this specific component.
 * * Strategy: Intra-Membership Verification Sieve
 * Run a low-link pass to assign every node to an explicit component ID map block. 
 * Initialize a new sub-adjacency matrix list for the query component. Iterate through the vertices of that component, 
 * checking their neighbors in the original graph. If a neighbor belongs to the same component, 
 * copy that edge into the new isolated sub-graph object.
 */
public class IsolatedSccExtractor {
    public static List<List<Integer>> extractSccAdjacency(int vertices, int querySccId, int[] componentMap, List<List<Integer>> adj) {
        List<List<Integer>> subGraphAdj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) subGraphAdj.add(new ArrayList<>());

        for (int u = 0; u < vertices; u++) {
            // Process edges if and only if both source and target match the query component ID
            if (componentMap[u] == querySccId) {
                for (int v : adj.get(u)) {
                    if (componentMap[v] == querySccId) {
                        subGraphAdj.get(u).add(v); // Copy edge to the isolated component subgraph
                    }
                }
            }
        }
        return subGraphAdj;
    }

    public static void main(String[] args) {
        int[] compMap = {0, 0, 1}; // Nodes 0 and 1 belong to SCC 0; Node 2 to SCC 1
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 3; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0); adj.get(1).add(2);

        System.out.println("Extracted Subgraph Adjacency for SCC 0: " + extractSccAdjacency(3, 0, compMap, adj)); // [[1], [0], []]
    }
}