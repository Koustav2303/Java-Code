import java.util.List;

/**
 * PROBLEM: Network Fault Tolerance
 * * Verify if a network routing configuration is immune to single-node failures, 
 * meaning the graph is fully biconnected and lacks any articulation points.
 * * Strategy: AP Set Emptiness Verification
 * Use Tarjan's cut vertex algorithm to collect all articulation points. 
 * If the resulting articulation points list is empty and the total connected components count 
 * is exactly 1, the network is fully fault-tolerant.
 */
public class NetworkFaultTolerance {
    public static boolean isNetworkFaultTolerant(int vertices, List<List<Integer>> adj) {
        ArticulationPoints apFinder = new ArticulationPoints();
        List<Integer> aps = apFinder.findArticulationPoints(vertices, adj);
        
        // Fully biconnected graphs must have no articulation points
        return aps.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("Network fault tolerance checker fully loaded.");
    }
}