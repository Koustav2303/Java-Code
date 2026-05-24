import java.util.Arrays;

public class MinPathSum {
    public static int minPathSum(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) grid[i][j] += grid[i][j - 1]; // Can only come from left
                else if (j == 0) grid[i][j] += grid[i - 1][j]; // Can only come from above
                else grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1}, {1,5,1}, {4,2,1}};
        System.out.println("Minimum Path Sum: " + minPathSum(grid));
    }
}