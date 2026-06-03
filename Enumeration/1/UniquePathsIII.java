/**
 * PROBLEM: Unique Paths III
 * * You are given an m x n integer array grid where you must find the number of 4-directional paths 
 * from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 * * Key identifiers:
 * 1 represents the starting square. 2 represents the ending square.
 * 0 represents empty squares we can walk on. -1 represents obstacles that we cannot walk on.
 * * Strategy: Hamiltonian Path Configuration Enumeration
 * Count the total number of empty walking squares first. Launch a grid-search DFS from the starting square. 
 * Track steps walked along the path. A path is accepted if and only if we hit square '2' and our steps count 
 * exactly matches our total empty spaces pool quota target.
 * * Complexity:
 * Time Complexity: O(4^(M*N)) brute-force grid space scaling exploration bounds.
 * Space Complexity: O(M*N) grid depth calls tracker.
 */
public class UniquePathsIII {
    private static int totalPaths = 0;

    public static int uniquePathsIII(int[][] grid) {
        totalPaths = 0; // Reset tracking metrics
        int m = grid.length, n = grid[0].length;
        int startRow = 0, startCol = 0;
        int emptySpacesCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    startRow = i; startCol = j;
                } else if (grid[i][j] == 0) {
                    emptySpacesCount++; // Build exact path checklist quota metrics
                }
            }
        }

        dfs(grid, startRow, startCol, 0, emptySpacesCount + 1); // +1 includes start square capacity
        return totalPaths;
    }

    private static void dfs(int[][] grid, int r, int c, int stepCount, int spaceTarget) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == -1) {
            return;
        }

        // Verification check goal reached
        if (grid[r][c] == 2) {
            if (stepCount == spaceTarget) {
                totalPaths++; // Complete path found
            }
            return;
        }

        int tempState = grid[r][c];
        grid[r][c] = -1; // Mark cell as visited directly inside current path tracking mask

        dfs(grid, r + 1, c, stepCount + 1, spaceTarget);
        dfs(grid, r - 1, c, stepCount + 1, spaceTarget);
        dfs(grid, r, c + 1, stepCount + 1, spaceTarget);
        dfs(grid, r, c - 1, stepCount + 1, spaceTarget);

        grid[r][c] = tempState; // Revert cell state back (Backtrack)
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 2, -1}
        };
        System.out.println("Total valid Hamiltonian paths count: " + uniquePathsIII(grid)); // 2
    }
}