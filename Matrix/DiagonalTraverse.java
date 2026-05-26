import java.util.Arrays;

public class DiagonalTraverse {
    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];
        
        int rows = mat.length;
        int cols = mat[0].length;
        int[] result = new int[rows * cols];
        
        int r = 0, c = 0;
        for (int i = 0; i < result.length; i++) {
            result[i] = mat[r][c];
            
            // Moving Up-Right
            if ((r + c) % 2 == 0) { 
                if (c == cols - 1) { r++; } // Hit right edge, move down
                else if (r == 0)   { c++; } // Hit top edge, move right
                else               { r--; c++; } // Move diagonally up-right
            } 
            // Moving Down-Left
            else { 
                if (r == rows - 1) { c++; } // Hit bottom edge, move right
                else if (c == 0)   { r++; } // Hit left edge, move down
                else               { r++; c--; } // Move diagonally down-left
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Diagonal Traverse: " + Arrays.toString(findDiagonalOrder(matrix)));
        // Output: [1, 2, 4, 7, 5, 3, 6, 8, 9]
    }
}