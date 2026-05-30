/**
 * PROBLEM: Toeplitz Matrix
 * * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 * * Example:
 * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * Output: true
 * * Approach:
 * Instead of extracting and checking each diagonal explicitly, we can simply simulate scanning 
 * the matrix. A matrix is Toeplitz if and only if EVERY element is equal to its top-left neighbor.
 */
public class ToeplitzMatrix {
    public static boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Start from row 1, col 1, checking against row-1, col-1
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 1, 2, 3},
            {9, 5, 1, 2}
        };
        System.out.println("Is Toeplitz Matrix? " + isToeplitzMatrix(matrix)); // true
    }
}