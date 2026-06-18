import java.util.List;

/**
 * PROBLEM: Unique Cycle SCC Check
 * * Verify if a specific Strongly Connected Component contains exactly one unique simple cycle loop.
 * * Strategy: Induced Edge-Vertex Count Invariant
 * Isolate the edges belonging to the target component. Count the number of unique vertices $V_c$ 
 * and edges $E_c$ inside this component. According to graph theory invariants, a strongly connected 
 * component forms a single unique simple cycle if and only if its edge count matches its vertex count: 
 * $E_c == V_c$.
 */
public class UniqueCycleSccCheck {
    public static boolean hasUniqueSimpleCycle(List<Integer> sccNodes, List<List<Integer>> adj) {
        boolean[] isMember = new boolean[adj.size()];
        for (int node : sccNodes) isMember[node] = true;

        int edgeCount = 0;
        for (int u : sccNodes) {
            for (int v : adj.get(u)) {
                if (isMember[v]) {
                    edgeCount++; // Count edges that connect vertices within the same component
                }
            }
        }

        // A biconnected directed component has a single cycle if and only if its vertex count equals its edge count
        return edgeCount == sccNodes.size();
    }

    public static void main(String[] args) {
        List<Integer> simpleCycleScc = java.util.Arrays.asList(0, 1, 2);
        System.out.println("Unique Cycle SCC structural checker framework configured.");
    }
}