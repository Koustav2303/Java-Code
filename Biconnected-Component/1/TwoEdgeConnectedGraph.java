import java.util.List;

/**
 * PROBLEM: Two-Edge Connected Graph
 * * Determine if an entire undirected graph is a valid 2-edge-connected graph, 
 * meaning it contains no bridges.
 * * Strategy: Bridge Tracking Sieve
 * Run the Tarjan bridge-finding algorithm on the graph. If the returned bridges list 
 * is completely empty, the graph is 2-edge-connected.
 */
public class TwoEdgeConnectedGraph {
    public static boolean is2EdgeConnected(int vertices, List<List<Integer>> adj) {
        BridgeFinding bridgeFinder = new BridgeFinding();
        List<String> bridges = bridgeFinder.findBridges(vertices, adj);
        return bridges.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("2-Edge connectivity structural sieve complete.");
    }
}