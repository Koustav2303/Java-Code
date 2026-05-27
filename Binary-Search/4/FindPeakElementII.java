import java.util.Arrays;

public class FindPeakElementII {
    public static int[] findPeakGrid(int[][] mat) {
        int startCol = 0, endCol = mat[0].length - 1;
        
        while (startCol <= endCol) {
            int midCol = startCol + (endCol - startCol) / 2;
            
            // Find the maximum element in the middle column
            int maxRow = 0;
            for (int i = 0; i < mat.length; i++) {
                if (mat[i][midCol] > mat[maxRow][midCol]) {
                    maxRow = i;
                }
            }
            
            // Evaluate adjacent columns
            boolean leftIsGreater = midCol - 1 >= startCol && mat[maxRow][midCol - 1] > mat[maxRow][midCol];
            boolean rightIsGreater = midCol + 1 <= endCol && mat[maxRow][midCol + 1] > mat[maxRow][midCol];
            
            if (!leftIsGreater && !rightIsGreater) {
                return new int[]{maxRow, midCol}; // Peak found
            } else if (rightIsGreater) {
                startCol = midCol + 1; // Climb toward the higher right side
            } else {
                endCol = midCol - 1; // Climb toward the higher left side
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 4}, {3, 2}};
        System.out.println("2D Peak found at index: " + Arrays.toString(findPeakGrid(mat))); // [0, 1]
    }
}