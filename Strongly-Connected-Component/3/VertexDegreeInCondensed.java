import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Vertex Degree in Condensed Graph
 * * Map out the explicit structural in-degrees and out-degrees of every individual meta-node 
 * inside the condensation graph of a directed graph network.
 * * Strategy: Cross-Component Adjacency Sifting
 * Decompose the graph using a low-link pass to assign every node to a component ID map. 
 * Initialize in-degree and out-degree tracking arrays of size equal to the component count. 
 * Iterate through every edge $(u, v)$ in the original graph. If `componentMap[u] != componentMap[v]`, 
 * increment `outDegree[componentMap[u]]` and `inDegree[componentMap[v]]`.
 */
public class VertexDegreeInCondensed {
    public static void computeCondensedDegrees(int vertices, int[] componentMap, int sccCount, List<List<Integer>> adj) {
        int[] inDegree = new int[sccCount];
        int[] outDegree = new int[sccCount];

        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) {
                int sccU = componentMap[u];
                int sccV = componentMap[v];
                if (sccU != sccV) {
                    outDegree[sccU]++;
                    inDegree[sccV]++;
                }
            }
        }

        for (int i = 0; i < sccCount; i++) {
            System.out.println("Meta-Node SCC #" + i + " -> In-Degree: " + inDegree[i] + ", Out-Degree: " + outDegree[i]);
        }
    }

    public static void main(String[] args) {
        int[] compMap = {0, 0, 1}; // Nodes 0 and 1 belong to SCC 0; Node 2 to SCC 1
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 3; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(2);

        computeCondensedDegrees(3, compMap, 2, adj);
    }
}