public class KthSmallestSortedMatrix {
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            
            // Count how many numbers are less than or equal to mid
            int count = countLessOrEqual(matrix, mid, n);
            
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // Starts at the bottom-left corner
    private static int countLessOrEqual(int[][] matrix, int target, int n) {
        int count = 0;
        int row = n - 1, col = 0;
        
        while (row >= 0 && col < n) {
            if (matrix[row][col] <= target) {
                count += row + 1; // All elements above this in the column are also smaller
                col++;
            } else {
                row--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,  5,  9},
            {10, 11, 13},
            {12, 13, 15}
        };
        int k = 8;
        System.out.println("The " + k + "th smallest element is: " + kthSmallest(matrix, k));
    }
}