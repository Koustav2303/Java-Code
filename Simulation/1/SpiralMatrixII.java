/**
 * PROBLEM: Spiral Matrix II
 * * Given a positive integer n, generate an n x n matrix filled with elements 
 * from 1 to n^2 in spiral order.
 * * Example:
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * * Approach:
 * Identical to Spiral Matrix I, but instead of reading values, we write an 
 * incrementing counter into the matrix cells.
 */

import java.util.Arrays;

public class SpiralMatrixII {
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        int num = 1;
        
        while (top <= bottom && left <= right) {
            // Write Right
            for (int i = left; i <= right; i++) matrix[top][i] = num++;
            top++;
            
            // Write Down
            for (int i = top; i <= bottom; i++) matrix[i][right] = num++;
            right--;
            
            // Write Left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) matrix[bottom][i] = num++;
                bottom--;
            }
            
            // Write Up
            if (left <= right) {
                for (int i = bottom; i >= top; i--) matrix[i][left] = num++;
                left++;
            }
        }
        
        return matrix;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] result = generateMatrix(n);
        System.out.println("Generated Spiral Matrix:");
        for (int[] row : result) System.out.println(Arrays.toString(row));
    }
}