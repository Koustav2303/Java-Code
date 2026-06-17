import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PROBLEM: Cactus Graph Checker
 * * Determine if a connected undirected graph is a valid Cactus Graph (a graph where any two simple 
 * cycles share at most one single common vertex).
 * * Strategy: Block Structure Degree Check
 * A connected graph is a cactus graph if and only if every biconnected component is either:
 * 1. A single structural bridge edge.
 * 2. A simple cycle ring (where every vertex inside the component has an induced degree of exactly 2).
 */
public class CactusGraphCheck {
    public static boolean isCactusGraph(int vertices, List<List<BccDiameter.Edge>> bccs) {
        for (List<BccDiameter.Edge> bcc : bccs) {
            if (bcc.size() == 1) continue; // Single bridge edge component block is perfectly valid

            Set<Integer> activeNodes = new HashSet<>();
            for (BccDiameter.Edge e : bcc) { activeNodes.add(e.u); activeNodes.add(e.v); }

            int maxNodeIndex = 0;
            for (int node : activeNodes) maxNodeIndex = Math.max(maxNodeIndex, node);

            int[] localDegrees = new int[maxNodeIndex + 1];
            for (BccDiameter.Edge e : bcc) {
                localDegrees[e.u]++;
                localDegrees[e.v]++;
            }

            // In a simple cycle block, every vertex must have a local component degree of exactly 2
            for (int node : activeNodes) {
                if (localDegrees[node] != 2) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Cactus Graph validation structural checker online.");
    }
}