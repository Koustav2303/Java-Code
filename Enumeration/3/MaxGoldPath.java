/**
 * PROBLEM: Path with Maximum Gold
 * * In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold 
 * in that cell. 0 means it is empty. Return the maximum amount of gold you can collect under the conditions:
 * - Every time you are located in a cell you will collect all the gold in that cell.
 * - From your position, you can walk one step to the left, right, up, or down.
 * - You can't visit the same cell more than once. You never visit a cell with 0 gold.
 * * Strategy: Multi-Root Grid Backtracking Search
 * Launch a DFS tracking path sums from every grid entry cell containing gold > 0. Mark cells as visited 
 * by setting their local gold value to 0 temporarily, then restore the original value upon backtracking.
 */
public class MaxGoldPath {
    public static int getMaximumGold(int[][] grid) {
        int maxGold = 0;
        int m = grid.length, n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    maxGold = Math.max(maxGold, dfs(grid, i, j));
                }
            }
        }
        return maxGold;
    }

    private static int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        int currentGold = grid[r][c];
        grid[r][c] = 0; // In-place mask lock step (Mark as visited)

        // Explore all four directions
        int maxNeighborGold = 0;
        maxNeighborGold = Math.max(maxNeighborGold, dfs(grid, r + 1, c));
        maxNeighborGold = Math.max(maxNeighborGold, dfs(grid, r - 1, c));
        maxNeighborGold = Math.max(maxNeighborGold, dfs(grid, r, c + 1));
        maxNeighborGold = Math.max(maxNeighborGold, dfs(grid, r, c - 1));

        grid[r][c] = currentGold; // Release cell mask back (Backtrack)
        return currentGold + maxNeighborGold;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 6, 0},
            {5, 8, 7},
            {0, 9, 0}
        };
        System.out.println("Max gold collectible: " + getMaximumGold(grid)); // 24 (Path: 9 -> 8 -> 7)
    }
}