public class MaxAreaOfIsland {
    public static int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, r, c));
                }
            }
        }
        return maxArea;
    }
    
    private static int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0; 
        return 1 + dfs(grid, r + 1, c) + dfs(grid, r - 1, c) 
                 + dfs(grid, r, c + 1) + dfs(grid, r, c - 1);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 1, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {1, 1, 0, 1, 1}
        };
        System.out.println("Max area of island: " + maxAreaOfIsland(grid)); // 4
    }
}