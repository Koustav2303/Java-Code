import java.util.Arrays;

public class SetMatrixZeroes {
    public static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;

        // 1. Check if first row/col have zeroes
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) firstRowHasZero = true;
        }
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) firstColHasZero = true;
        }

        // 2. Use first row/col as markers for the rest of the matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 3. Zero out based on markers
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 4. Zero out the first row/col if needed
        if (firstRowHasZero) {
            for (int j = 0; j < cols; j++) matrix[0][j] = 0;
        }
        if (firstColHasZero) {
            for (int i = 0; i < rows; i++) matrix[i][0] = 0;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        setZeroes(matrix);
        System.out.println("Matrix after setting zeroes:");
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
    }
}