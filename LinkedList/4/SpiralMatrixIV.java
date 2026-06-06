import java.util.Arrays;

/**
 * PROBLEM: Spiral Matrix IV
 * * You are given two integers m and n, which represent the dimensions of a matrix.
 * You are also given the head of a linked list of integers. Generate an m x n matrix containing 
 * the integers from the linked list, filled in a clockwise spiral order. 
 * If there are remaining empty spaces, fill them with -1.
 * * Strategy: Boundary Collision Steering
 * Initialize an $m \times n$ matrix filled with -1. Define tracking vectors for your row and column boundaries. 
 * Traverse the linked list and populate the matrix slots sequentially. 
 * When your index hits a boundary or an already-filled slot, rotate your direction vector 90 degrees clockwise 
 * and continue.
 */
public class SpiralMatrixIV {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];
        for (int[] row : matrix) Arrays.fill(row, -1); // Initialize empty slots with -1

        int topRow = 0, bottomRow = m - 1;
        int leftCol = 0, rightCol = n - 1;
        
        ListNode curr = head;

        while (curr != null && topRow <= bottomRow && leftCol <= rightCol) {
            // Traverse from left to right across the top row boundary
            for (int j = leftCol; j <= rightCol && curr != null; j++) {
                matrix[topRow][j] = curr.val;
                curr = curr.next;
            }
            topRow++;

            // Traverse from top to bottom down the right column boundary
            for (int i = topRow; i <= bottomRow && curr != null; i++) {
                matrix[i][rightCol] = curr.val;
                curr = curr.next;
            }
            rightCol--;

            // Traverse from right to left across the bottom row boundary
            for (int j = rightCol; j >= leftCol && curr != null; j--) {
                matrix[bottomRow][j] = curr.val;
                curr = curr.next;
            }
            bottomRow--;

            // Traverse from bottom to top up the left column boundary
            for (int i = bottomRow; i >= topRow && curr != null; i--) {
                matrix[i][leftCol] = curr.val;
                curr = curr.next;
            }
            leftCol++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2); head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        int[][] res = spiralMatrix(3, 3, head); // Populate a 3x3 matrix spiral grid layout
        System.out.println("Spiral Matrix Projection Output: " + Arrays.deepToString(res));
        // [[1, 2, 3], [-1, -1, 4], [-1, -1, -1]]
    }
}