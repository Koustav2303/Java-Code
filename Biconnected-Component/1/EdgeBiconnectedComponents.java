import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PROBLEM: Edge-Biconnected Components
 * * Partition an undirected graph into maximal subgraphs where every pair of vertices within 
 * a component is connected by at least two edge-disjoint paths.
 * * Strategy: Bridge Erasure Pipeline
 * Find all bridges in the graph and store them in a fast-lookup filtering Set. 
 * Run a secondary standard DFS/BFS pass across the graph. When traversing, refuse to cross any edge 
 * that is marked as a bridge. This cleanly groups the graph into isolated 2-edge-connected components.
 */
public class EdgeBiconnectedComponents {
    public List<List<Integer>> find2EdgeConnectedComponents(int vertices, List<List<Integer>> adj) {
        BridgeFinding bridgeFinder = new BridgeFinding();
        List<String> bridgeList = bridgeFinder.findBridges(vertices, adj);
        Set<String> bridgeSet = new HashSet<>(bridgeList);

        boolean[] visited = new boolean[vertices];
        List<List<Integer>> components = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfsCollect(i, visited, component, bridgeSet, adj);
                components.add(component);
            }
        }
        return components;
    }

    private void dfsCollect(int u, boolean[] visited, List<Integer> component, Set<String> bridgeSet, List<List<Integer>> adj) {
        visited[u] = true;
        component.add(u);

        for (int v : adj.get(u)) {
            if (visited[v]) continue;
            
            // Check if the edge (u, v) is a bridge to prevent crossing it
            String edgeKey1 = u + "-" + v;
            String edgeKey2 = v + "-" + u;
            if (bridgeSet.contains(edgeKey1) || bridgeSet.contains(edgeKey2)) continue;

            dfsCollect(v, visited, component, bridgeSet, adj);
        }
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2);
        adj.get(2).add(3); adj.get(3).add(2);

        EdgeBiconnectedComponents solver = new EdgeBiconnectedComponents();
        System.out.println("2-Edge Connected Components: " + solver.find2EdgeConnectedComponents(vertices, adj));
        // Expected grouping: [[0, 1, 2], [3]]
    }
}