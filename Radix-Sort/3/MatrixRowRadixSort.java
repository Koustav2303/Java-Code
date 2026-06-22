import java.util.Arrays;

/**
 * PROBLEM: Matrix Row Radix Sort
 * * Sort the rows of a 2D integer grid matrix lexicographically across all columns without using comparison loops.
 * * Strategy: Right-to-Left Column Matrix Sieve
 * Treat columns as keys with decreasing significance from left to right. 
 * Run sequential, stable LSD radix sort passes on each column index, moving backwards 
 * from the last column down to the first column.
 */
public class MatrixRowRadixSort {
    public static void sortMatrix(int[][] matrix) {
        if (matrix == null || matrix.length <= 1 || matrix[0].length == 0) return;

        int rows = matrix.length;
        int cols = matrix[0].length;

        // FIXED: Loop variable changed from 'c' to 'd' to match the loop body variables
        for (int d = cols - 1; d >= 0; d--) {
            int max = 0;
            for (int i = 0; i < rows; i++) max = Math.max(max, matrix[i][d]);

            // Execute an LSD pass on the current target column
            matrixColumnStableSort(matrix, d, max);
        }
    }

    private static void matrixColumnStableSort(int[][] matrix, int col, int max) {
        int n = matrix.length;
        int[][] output = new int[n][];
        
        for (int exp = 1; max / exp > 0; exp *= 10) {
            int[] count = new int[10];

            for (int i = 0; i < n; i++) {
                int digit = (matrix[i][col] / exp) % 10;
                count[digit]++;
            }

            for (int i = 1; i < 10; i++) count[i] += count[i - 1];

            for (int i = n - 1; i >= 0; i--) {
                int digit = (matrix[i][col] / exp) % 10;
                output[count[digit] - 1] = matrix[i];
                count[digit]--;
            }
            System.arraycopy(output, 0, matrix, 0, n);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {2, 5},
            {1, 9},
            {2, 1},
            {1, 2}
        };
        sortMatrix(matrix);
        System.out.println("Lexicographically Sorted Matrix rows: " + Arrays.deepToString(matrix));
        // [[1, 2], [1, 9], [2, 1], [2, 5]]
    }
}