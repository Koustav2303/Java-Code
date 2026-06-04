import java.util.*;

/**
 * PROBLEM: Swim in Rising Water
 * * You are given an n x n integer grid where each cell grid[r][c] represents the elevation at that point.
 * Rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a cell to another 
 * 4-directionally adjacent cell if and only if both elevations are at most t.
 * Return the least time until you can reach the bottom right cell (n-1, n-1) starting from the top left cell (0, 0).
 * * Strategy: Sorted Cell Weight Activation Matrix
 * Collect all coordinates and sort them by elevation values. Progressively activate cells from lowest 
 * to highest elevation, unioning each newly activated cell with its active neighbors. Stop and return the time 
 * as soon as cell (0,0) shares the same component root as cell (n-1, n-1).
 */
public class SwimInRisingWaterDSU {
    static class SwimDSU {
        int[] parent;
        public SwimDSU(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) parent[rootP] = rootQ;
        }
    }

    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        List<int[]> cells = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                cells.add(new int[]{r, c, grid[r][c]});
            }
        }
        
        // Sort elements by raw elevation values ascending
        cells.sort((a, b) -> Integer.compare(a[2], b[2]));
        SwimDSU dsu = new SwimDSU(n * n);
        boolean[][] activated = new boolean[n][n];
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        for (int[] cell : cells) {
            int r = cell[0], c = cell[1], time = cell[2];
            activated[r][c] = true;
            int currentId = r * n + c;

            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && activated[nr][nc]) {
                    dsu.union(currentId, nr * n + nc);
                }
            }

            // Connection destination threshold goal verification check
            if (activated[0][0] && activated[n - 1][n - 1] && dsu.find(0) == dsu.find(n * n - 1)) {
                return time;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 2},
            {1, 3}
        };
        System.out.println("Minimum safe structural delay duration: " + swimInWater(grid)); // 3
    }
}