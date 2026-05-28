/**
 * PROBLEM: Range Sum Query 2D - Immutable
 * * Given a 2D matrix matrix, handle multiple queries of the following type:
 * Calculate the sum of the elements of matrix inside the rectangle defined by its 
 * upper left corner (row1, col1) and lower right corner (row2, col2).
 * * Approach:
 * We precompute a 2D Prefix Sum matrix. 
 * dp[r][c] stores the sum of all elements in the rectangle from (0,0) to (r-1,c-1).
 * The sum of any region (r1, c1) to (r2, c2) uses the Inclusion-Exclusion Principle:
 * RegionSum = dp[r2][c2] - dp[r1][c2] - dp[r2][c1] + dp[r1][c1].
 */
public class RangeSumQuery2D {
    private int[][] dp;

    public RangeSumQuery2D(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Add a 1-cell buffer on top and left to avoid out-of-bounds checks
        dp = new int[m + 1][n + 1];
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // Inclusion-Exclusion Principle for building the 2D Prefix Sum
                dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] - dp[r][c] + matrix[r][c];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // Inclusion-Exclusion Principle for querying
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };
        RangeSumQuery2D obj = new RangeSumQuery2D(matrix);
        System.out.println("Region Sum (2,1 to 4,3): " + obj.sumRegion(2, 1, 4, 3)); // 8
    }
}