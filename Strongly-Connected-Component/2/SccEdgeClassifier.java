import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: SCC Edge Classifier
 * * Classify every edge in a directed graph into one of two categories: 
 * 1. Intra-Component Edges: Edges whose endpoints reside within the same SCC (cycle-forming edges).
 * 2. Inter-Component Edges: Edges that cross between different SCCs (bridge/transit edges).
 * * Strategy: Mapping Equality Sieve
 * Decompose the graph using a single-pass low-link tracker to create a component mapping array. 
 * Iterate through every edge $(u, v)$ in the graph. If `componentMap[u] == componentMap[v]`, 
 * classify the edge as Intra-Component; otherwise, classify it as Inter-Component.
 */
public class SccEdgeClassifier {
    public static void classifyGraphEdges(int vertices, int[] componentMap, List<List<Integer>> adj) {
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                if (componentMap[u] == componentMap[v]) {
                    System.out.println("Edge (" + u + " -> " + v + ") is an INTRA-component edge.");
                } else {
                    System.out.println("Edge (" + u + " -> " + v + ") is an INTER-component edge.");
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] compMap = {0, 0, 1}; // Nodes 0 and 1 belong to SCC 0; Node 2 to SCC 1
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 3; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(2);

        classifyGraphEdges(3, compMap, adj);
    }
}