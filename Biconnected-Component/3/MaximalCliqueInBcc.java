import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PROBLEM: Maximal Clique Bounded in BCC
 * * Find the size of the largest complete subgraph (clique) embedded within any single 
 * biconnected component of an undirected graph.
 * * Strategy: Block-Level Induced Clique Search
 * A complete clique subgraph cannot span across any articulation point bottlenecks. 
 * Therefore, the global maximum clique must reside entirely within one of the graph's BCC blocks. 
 * Decompose the graph into BCC components, isolate the vertices for each block, and execute a local Bron-Kerbosch 
 * clique search on the induced subgraph of each component.
 */
public class MaximalCliqueInBcc {
    private int maxBccCliqueSize = 0;

    public int findMaximalClique(int n, List<List<BccDiameter.Edge>> bccs, List<List<Integer>> globalAdj) {
        for (List<BccDiameter.Edge> bcc : bccs) {
            Set<Integer> bccNodes = new HashSet<>();
            for (BccDiameter.Edge e : bcc) { bccNodes.add(e.u); bccNodes.add(e.v); }
            
            List<Integer> nodesList = new ArrayList<>(bccNodes);
            runBronKerbosch(new ArrayList<>(), nodesList, new ArrayList<>(), localAdjacencyMatrix(nodesList, globalAdj));
        }
        return maxBccCliqueSize;
    }

    private boolean[][] localAdjacencyMatrix(List<Integer> nodes, List<List<Integer>> globalAdj) {
        int sz = nodes.size();
        boolean[][] matrix = new boolean[sz][sz];
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                if (globalAdj.get(nodes.get(i)).contains(nodes.get(j))) matrix[i][j] = true;
            }
        }
        return matrix;
    }

    private void runBronKerbosch(List<Integer> r, List<Integer> p, List<Integer> x, boolean[][] adj) {
        if (p.isEmpty() && x.isEmpty()) {
            maxBccCliqueSize = Math.max(maxBccCliqueSize, r.size());
            return;
        }
        List<Integer> pCopy = new ArrayList<>(p);
        for (int v : pCopy) {
            List<Integer> newR = new ArrayList<>(r); newR.add(v);
            List<Integer> newP = new ArrayList<>();
            List<Integer> newX = new ArrayList<>();
            for (int u : p) if (adj[v][u]) newP.add(u);
            for (int u : x) if (adj[v][u]) newX.add(u);

            runBronKerbosch(newR, newP, newX, adj);
            p.remove((Integer) v);
            x.add(v);
        }
    }

    public static void main(String[] args) {
        System.out.println("Bron-Kerbosch maximal sub-clique inside BCC processor online.");
    }
}