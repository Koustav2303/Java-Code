import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PROBLEM: Path Independent of Articulation Points
 * * Given a source vertex s and a sink vertex t inside a biconnected graph, extract two separate 
 * paths connecting s and t that share absolutely zero intermediate internal vertices.
 * * Strategy: Residual Network Augmentation
 * According to Menger's Theorem, a graph is biconnected if and only if any two vertices are connected 
 * by at least two vertex-disjoint paths.
 * 1. Find a standard path from s to t using DFS.
 * 2. Temporarily remove all internal vertices along this path from the graph.
 * 3. Attempt to find a second path from s to t using the remaining available vertices.
 */
public class PathIndependentAP {
    public List<List<Integer>> findVertexDisjointPaths(int vertices, List<List<Integer>> adj, int s, int t) {
        List<List<Integer>> resultPaths = new ArrayList<>();
        boolean[] blocked = new boolean[vertices];

        // Path 1 Lookup sequence
        List<Integer> path1 = new ArrayList<>();
        if (!dfsPathFind(s, t, blocked, path1, adj)) return resultPaths;

        resultPaths.add(new ArrayList<>(path1));

        // Block internal nodes along path 1 to force path 2 to use independent vertices
        for (int node : path1) {
            if (node != s && node != t) blocked[node] = true;
        }

        // Path 2 Lookup sequence
        List<Integer> path2 = new ArrayList<>();
        if (dfsPathFind(s, t, blocked, path2, adj)) {
            resultPaths.add(new ArrayList<>(path2));
        }

        return resultPaths;
    }

    private boolean dfsPathFind(int curr, int target, boolean[] blocked, List<Integer> path, List<List<Integer>> adj) {
        blocked[curr] = true;
        path.add(curr);

        if (curr == target) return true;

        for (int neighbor : adj.get(curr)) {
            if (!blocked[neighbor]) {
                if (dfsPathFind(neighbor, target, blocked, path, adj)) return true;
            }
        }
        path.remove(path.size() - 1); // Unwind recursion stack
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Disjoint path independent validation frameworks enabled.");
    }
}