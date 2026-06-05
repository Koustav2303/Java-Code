import java.util.*;

/**
 * PROBLEM: Making A Large Island
 * * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to 1.
 * Return the size of the largest island in grid after applying this operation.
 * An island is a 4-directionally connected group of 1s.
 * * Strategy: Pre-calculated Set Weight Aggregation
 * Run a native Union-Find step across all initial '1' clusters, tracking the sizes of each component root. 
 * Then, iterate through every '0' cell. Check its 4-directional unique neighbor roots, sum their 
 * pre-calculated component sizes, and add 1 (for the converted cell) to locate the maximum potential area.
 */
public class MaximumConnectedGroup {
    static class GridDSU {
        int[] parent;
        int[] size;
        public GridDSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) { parent[i] = i; size[i] = 1; }
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
        }
    }

    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        GridDSU dsu = new GridDSU(n * n);
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        int maxIsland = 0;
        boolean hasZero = false;

        // Pass 1: Union adjacent 1s
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    int id = r * n + c;
                    maxIsland = Math.max(maxIsland, dsu.size[dsu.find(id)]);
                    if (r + 1 < n && grid[r + 1][c] == 1) dsu.union(id, (r + 1) * n + c);
                    if (c + 1 < n && grid[r][c + 1] == 1) dsu.union(id, r * n + (c + 1));
                }
            }
        }

        // Pass 2: Evaluate 0 conversions
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    hasZero = true;
                    Set<Integer> uniqueRoots = new HashSet<>();
                    for (int[] d : dirs) {
                        int nr = r + d[0], nc = c + d[1];
                        if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                            uniqueRoots.add(dsu.find(nr * n + nc));
                        }
                    }
                    int combinedSize = 1;
                    for (int root : uniqueRoots) {
                        combinedSize += dsu.size[root];
                    }
                    maxIsland = Math.max(maxIsland, combinedSize);
                }
            }
        }
        return hasZero ? maxIsland : n * n;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0}, {0, 1}};
        System.out.println("Maximum potential island area: " + largestIsland(grid)); // 3
    }
}