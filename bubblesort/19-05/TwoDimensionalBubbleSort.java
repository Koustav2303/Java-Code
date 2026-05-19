import java.util.Arrays;

public class TwoDimensionalBubbleSort {
    public static void sortRows(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            int n = matrix[row].length;
            // Standard bubble sort applied to matrix[row]
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (matrix[row][j] > matrix[row][j + 1]) {
                        int temp = matrix[row][j];
                        matrix[row][j] = matrix[row][j + 1];
                        matrix[row][j + 1] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {9, 4, 6, 2},
            {15, 1, 8, 3},
            {7, 12, 5, 11}
        };
        
        System.out.println("Original Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        
        sortRows(matrix);
        
        System.out.println("\nMatrix with sorted rows:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}