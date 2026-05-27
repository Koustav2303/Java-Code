public class PathWithMaximumGold {
    public static int getMaximumGold(int[][] grid) {
        int maxGold = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    maxGold = Math.max(maxGold, dfs(grid, i, j));
                }
            }
        }
        return maxGold;
    }

    private static int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        int currentGold = grid[i][j];
        grid[i][j] = 0; // Mark as visited by stripping its gold

        int maxPath = 0;
        maxPath = Math.max(maxPath, dfs(grid, i + 1, j));
        maxPath = Math.max(maxPath, dfs(grid, i - 1, j));
        maxPath = Math.max(maxPath, dfs(grid, i, j + 1));
        maxPath = Math.max(maxPath, dfs(grid, i, j - 1));

        grid[i][j] = currentGold; // Backtrack

        return currentGold + maxPath;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 6, 0},
            {5, 8, 7},
            {0, 9, 0}
        };
        System.out.println("Maximum gold collected: " + getMaximumGold(grid)); // 24 (9 -> 8 -> 7)
    }
}