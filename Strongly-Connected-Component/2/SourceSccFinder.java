import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Source SCC Finder
 * * Identify all "source" Strongly Connected Components inside a directed graph. 
 * A source SCC has an in-degree of 0 in the condensation graph, meaning no external edges point into it.
 * * Strategy: Cross-Component In-Degree Sieve
 * Decompose the graph using a low-link tracker to create a component mapping array. 
 * Initialize a boolean array `hasIncomingEdge` of size equal to the number of components. 
 * Iterate through every edge $(u, v)$ in the graph. If `componentMap[u] != componentMap[v]`, 
 * mark the target component as having an incoming edge. Components that remain unmarked are source SCCs.
 */
public class SourceSccFinder {
    public static List<Integer> locateSourceSccs(int vertices, int[] compMap, int sccCount, List<List<Integer>> adj) {
        boolean[] hasIncomingEdge = new boolean[sccCount];

        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                int sccU = compMap[u];
                int sccV = compMap[v];
                if (sccU != sccV) {
                    hasIncomingEdge[sccV] = true; // Mark target component as a non-source meta-node
                }
            }
        }

        List<Integer> sourceSccIds = new ArrayList<>();
        for (int i = 0; i < sccCount; i++) {
            if (!hasIncomingEdge[i]) sourceSccIds.add(i);
        }
        return sourceSccIds;
    }

    public static void main(String[] args) {
        int[] compMap = {0, 0, 1}; // Components configuration mapping
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 3; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(2); // SCC 0 points to SCC 1

        System.out.println("Source SCC component IDs: " + locateSourceSccs(3, compMap, 2, adj)); // [0]
    }
}