/**
 * PROBLEM: Spiral Matrix
 * * Given an m x n matrix, return all elements of the matrix in spiral order.
 * * Example:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * * Approach:
 * Establish 4 boundaries (top, bottom, left, right).
 * Simulate walking the perimeter in the order: Right -> Down -> Left -> Up.
 * After each walk, shrink the respective boundary.
 */


import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;
        
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        
        while (top <= bottom && left <= right) {
            // Traverse Right
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++; // Shrink top boundary
            
            // Traverse Down
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // Shrink right boundary
            
            // Traverse Left (Check if top boundary crossed bottom to prevent duplicates)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--; // Shrink bottom boundary
            }
            
            // Traverse Up (Check if left boundary crossed right to prevent duplicates)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // Shrink left boundary
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Spiral Order: " + spiralOrder(matrix));
    }
}