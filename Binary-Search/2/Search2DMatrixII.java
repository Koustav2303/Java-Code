public class Search2DMatrixII {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int row = 0;
        int col = matrix[0].length - 1; // Start top-right
        
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--; // Target must be to the left
            } else {
                row++; // Target must be below
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        System.out.println("Target 5 found? " + searchMatrix(matrix, 5)); // true
        System.out.println("Target 20 found? " + searchMatrix(matrix, 20)); // false
    }
}