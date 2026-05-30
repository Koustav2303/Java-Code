import java.util.Arrays;

/**
 * PROBLEM: Diagonal Traverse
 * * Given an m x n matrix, return an array of all the elements of the array in a diagonal order.
 * * Example:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 * * Approach:
 * Simulate the traversal by tracking the direction (Up-Right or Down-Left).
 * When hitting a boundary, adjust the row/col pointers accordingly and flip the direction.
 */
public class DiagonalTraverse {
    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];
        
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        
        int r = 0, c = 0;
        boolean up = true;
        
        for (int i = 0; i < result.length; i++) {
            result[i] = mat[r][c];
            
            if (up) { // Moving Up-Right
                if (c == n - 1) { r++; up = false; } // Hit right border
                else if (r == 0) { c++; up = false; } // Hit top border
                else { r--; c++; }
            } else { // Moving Down-Left
                if (r == m - 1) { c++; up = true; } // Hit bottom border
                else if (c == 0) { r++; up = true; } // Hit left border
                else { r++; c--; }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Diagonal Traverse: " + Arrays.toString(findDiagonalOrder(matrix))); 
        // [1, 2, 4, 7, 5, 3, 6, 8, 9]
    }
}