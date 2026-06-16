import java.util.List;

/**
 * PROBLEM: Vertex Connectivity Structural Verifier
 * * Verify if the absolute vertex connectivity of a graph satisfies the property $\kappa(G) \ge 2$.
 * * Strategy: Connectivity Threshold Metric
 * According to graph theory, a graph has a vertex connectivity $\kappa(G) \ge 2$ if and only if 
 * it is globally connected, contains at least 3 vertices, and contains zero articulation points. 
 * Run Tarjan's cut-vertex detection algorithm to verify these constraints.
 */
public class VertexConnectivityTwo {
    public static boolean checkKappaThreshold(int vertices, List<List<Integer>> adj) {
        // A graph with fewer than 3 vertices cannot have a vertex connectivity >= 2 unless it is K3 or larger
        if (vertices < 3) return false;

        BiconnectedGraphCheck graphTester = new BiconnectedGraphCheck();
        
        // If the graph is fully biconnected and has >= 3 nodes, then kappa(G) >= 2
        return graphTester.isBiconnected(vertices, adj);
    }

    public static void main(String[] args) {
        System.out.println("Vertex Connectivity validation script ready.");
    }
}