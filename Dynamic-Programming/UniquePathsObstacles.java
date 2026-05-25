import java.util.Arrays;

public class UniquePathsObstacles {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int cols = obstacleGrid[0].length;
        int[] dp = new int[cols];
        dp[0] = 1; // Base case: 1 way to be at the start

        for (int[] row : obstacleGrid) {
            for (int j = 0; j < cols; j++) {
                if (row[j] == 1) {
                    // If there's an obstacle, ways to reach here is 0
                    dp[j] = 0;
                } else if (j > 0) {
                    // Otherwise, add the ways from the left cell
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[cols - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        System.out.println("Unique Paths around obstacles: " + uniquePathsWithObstacles(grid));
    }
}