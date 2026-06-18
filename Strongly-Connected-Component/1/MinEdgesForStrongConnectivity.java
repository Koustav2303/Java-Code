import java.util.List;
import java.util.Set;

/**
 * PROBLEM: Minimum Edges for Strong Connectivity
 * * Calculate the minimum number of directed edges that must be added to a directed graph 
 * to make the entire structure globally strongly connected.
 * * Strategy: Meta-Graph Source/Sink Balancing
 * Condense the graph into its topological SCC condensation graph. 
 * Calculate the in-degrees and out-degrees of each component meta-node. 
 * Count the number of source components (in-degree == 0) and sink components (out-degree == 0). 
 * The minimum number of edges needed to strongly connect the graph is given by `max(sources, sinks)`. 
 * If the original graph was already strongly connected, return 0.
 */
public class MinEdgesForStrongConnectivity {
    public static int minEdgesToConnect(int vertices, List<List<Integer>> adj) {
        int[] sccCountContainer = new int[1];
        List<Set<Integer>> condensedDAG = CondensationGraph.buildCondensationGraph(vertices, adj, sccCountContainer);
        int sccCount = sccCountContainer[0];

        if (sccCount <= 1) return 0; // Graph is already strongly connected

        int[] inDegree = new int[sccCount];
        int[] outDegree = new int[sccCount];

        for (int u = 0; u < sccCount; u++) {
            for (int v : condensedDAG.get(u)) {
                outDegree[u]++;
                inDegree[v]++;
            }
        }

        int sourcesCount = 0;
        int sinksCount = 0;
        for (int i = 0; i < sccCount; i++) {
            if (inDegree[i] == 0) sourcesCount++;
            if (outDegree[i] == 0) sinksCount++;
        }

        return Math.max(sourcesCount, sinksCount);
    }

    public static void main(String[] args) {
        System.out.println("DAG Source-Sink balance connectivity optimizer online.");
    }
}