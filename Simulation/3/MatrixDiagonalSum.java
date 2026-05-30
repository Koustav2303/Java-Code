/**
 * PROBLEM: Matrix Diagonal Sum
 * * Given a square matrix mat, return the sum of the matrix diagonals.
 * Only include the sum of all the elements on the primary diagonal and all the elements 
 * on the secondary diagonal that are not part of the primary diagonal.
 * * Approach:
 * Simulate the traversal. Add elements from (i, i) and (i, n-1-i).
 * If the matrix size is odd, the exact center element will be added twice, so subtract it once at the end.
 */
public class MatrixDiagonalSum {
    public static int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;
        
        for (int i = 0; i < n; i++) {
            sum += mat[i][i]; // Primary diagonal
            sum += mat[i][n - 1 - i]; // Secondary diagonal
        }
        
        // If odd size, subtract the center which was counted twice
        if (n % 2 != 0) {
            sum -= mat[n / 2][n / 2];
        }
        
        return sum;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Diagonal sum: " + diagonalSum(mat)); // 25
    }
}