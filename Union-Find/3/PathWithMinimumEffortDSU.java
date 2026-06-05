import java.util.*;

/**
 * PROBLEM: Path With Minimum Effort
 * * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size m x n.
 * You start at the top-left cell (0, 0) and want to travel to the bottom-right cell (m-1, n-1).
 * The effort of a path is the maximum absolute difference in heights between two consecutive cells.
 * Return the minimum effort required to travel from the start to the end.
 * * Strategy: Edge Threshold Injection
 * Instead of standard Dijkstra, treat this as a connectivity challenge. Extract all internal 4-directional 
 * grid cell transitions as explicit edges: `{nodeA, nodeB, absolute_elevation_delta}`. 
 * Sort edges by weight in ascending order. Inject edges into the DSU one by one. 
 * Stop and return the current edge's weight as soon as cell `0` and cell `m*n-1` share the same parent root.
 */
public class PathWithMinimumEffortDSU {
    static class Edge {
        int v1, v2, weight;
        public Edge(int v1, int v2, int w) { this.v1 = v1; this.v2 = v2; this.weight = w; }
    }

    static class EffortDSU {
        int[] parent;
        public EffortDSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP != rootQ) parent[rootP] = rootQ;
        }
    }

    public static int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        if (m == 1 && n == 1) return 0;

        List<Edge> edges = new ArrayList<>();
        // Extract all adjacent cell transition edge boundaries
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int currentId = r * n + c;
                if (r + 1 < m) edges.add(new Edge(currentId, (r + 1) * n + c, Math.abs(heights[r][c] - heights[r + 1][c])));
                if (c + 1 < n) edges.add(new Edge(currentId, r * n + (c + 1), Math.abs(heights[r][c] - heights[r][c + 1])));
            }
        }

        edges.sort((a, b) -> Integer.compare(a.weight, b.weight));
        EffortDSU dsu = new EffortDSU(m * n);

        for (Edge edge : edges) {
            dsu.union(edge.v1, edge.v2);
            // Verify if source and destination are connected
            if (dsu.find(0) == dsu.find(m * n - 1)) {
                return edge.weight;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println("Minimum required hike effort scale: " + minimumEffortPath(heights)); // 2
    }
}