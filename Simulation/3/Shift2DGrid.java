import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Shift 2D Grid
 * * Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
 * In one shift operation:
 * - Element at grid[i][j] moves to grid[i][j + 1].
 * - Element at grid[i][n - 1] moves to grid[i + 1][0].
 * - Element at grid[m - 1][n - 1] moves to grid[0][0].
 * * Approach:
 * Instead of simulating k full shifts (which is slow), map the 2D grid conceptually to a 1D array.
 * Calculate the new 1D index using math: (current_1d_index + k) % total_elements.
 * Then map the 1D index back to 2D coordinates.
 */
public class Shift2DGrid {
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        
        // Create the structure for the result
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) row.add(0);
            result.add(row);
        }
        
        k = k % total; // Optimize full loops
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index1D = i * n + j;
                int newIndex1D = (index1D + k) % total;
                
                int newRow = newIndex1D / n;
                int newCol = newIndex1D % n;
                
                result.get(newRow).set(newCol, grid[i][j]);
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Shifted Grid: " + shiftGrid(grid, 1)); 
        // [[9, 1, 2], [3, 4, 5], [6, 7, 8]]
    }
}