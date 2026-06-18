import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Sink SCC Finder
 * * Locate and extract the IDs of all "sink" Strongly Connected Components inside a directed graph. 
 * A sink SCC has an out-degree of 0 in the condensation graph, meaning no directed edges point outward from it.
 * * Strategy: Cross-Component Out-Degree Sieve
 * Decompose the graph using Tarjan's algorithm to create a component mapping array. 
 * Initialize a boolean array `hasOutgoingEdge` of size equal to the number of components. 
 * Iterate through every edge $(u, v)$ in the graph. If `componentMap[u] != componentMap[v]`, 
 * mark the source component as having an outgoing edge. Components that remain unmarked are sink SCCs.
 */
public class SinkSccFinder {
    public static List<Integer> locateSinkSccs(int vertices, int[] componentMap, int sccCount, List<List<Integer>> adj) {
        boolean[] hasOutgoingEdge = new boolean[sccCount];

        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                int sccU = componentMap[u];
                int sccV = componentMap[v];
                if (sccU != sccV) {
                    hasOutgoingEdge[sccU] = true; // Mark source component as a non-sink meta-node
                }
            }
        }

        List<Integer> sinkSccIds = new ArrayList<>();
        for (int i = 0; i < sccCount; i++) {
            if (!hasOutgoingEdge[i]) sinkSccIds.add(i);
        }
        return sinkSccIds;
    }

    public static void main(String[] args) {
        int[] compMap = {0, 0, 1}; // Nodes 0 and 1 belong to SCC 0; Node 2 to SCC 1
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 3; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(2); // SCC 0 points to SCC 1

        System.out.println("Sink SCC component IDs: " + locateSinkSccs(3, compMap, 2, adj)); // [1]
    }
}