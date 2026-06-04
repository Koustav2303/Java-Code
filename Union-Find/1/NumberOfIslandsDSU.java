import java.util.*;

/**
 * PROBLEM: Number of Islands
 * * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), 
 * return the number of islands. An island is surrounded by water and is formed by connecting 
 * adjacent lands horizontally or vertically.
 * * Strategy: 2D-to-1D Coordinate Flattening
 * Initialize each '1' cell as its own independent component. Flatten 2D coordinates into a 1D index 
 * using the mapping formula: index = r * n + c. Traverse the grid, unioning adjacent '1' cells 
 * and decrementing the total island component count accordingly.
 */
public class NumberOfIslandsDSU {
    static class UnionFind {
        int[] parent;
        int count;

        public UnionFind(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        int id = i * n + j;
                        parent[id] = id;
                        count++;
                    }
                }
            }
        }

        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]); // Path compression
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                count--; // Components merge, reducing net count
            }
        }
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0'; // Mark as visited to avoid double-processing
                    int currentId = r * n + c;

                    // Check 4-directional neighbors
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') uf.union(currentId, (r - 1) * n + c);
                    if (r + 1 < m && grid[r + 1][c] == '1') uf.union(currentId, (r + 1) * n + c);
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') uf.union(currentId, r * n + (c - 1));
                    if (c + 1 < n && grid[r][c + 1] == '1') uf.union(currentId, r * n + (c + 1));
                }
            }
        }
        return uf.count;
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println("Total distinct islands: " + numIslands(grid)); // 3
    }
}