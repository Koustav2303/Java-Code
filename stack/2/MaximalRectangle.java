import java.util.Stack;

/**
 * PROBLEM: Maximal Rectangle
 * * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle 
 * containing only 1's and return its area.
 * * Strategy:
 * Reduce this 2D problem to the 1D problem "Largest Rectangle in Histogram".
 * Maintain a heights array matching the number of columns. For each row, update the height values 
 * (if cell is '1', increment height; if it's '0', reset height to 0). Then evaluate using a monotonic stack.
 * * Complexity:
 * Time Complexity: O(R * C) where R is rows and C is columns.
 * Space Complexity: O(C) matching row widths.
 */
public class MaximalRectangle {
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;
        
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < cols; c++) {
                // Update heights based on consecutive 1s
                heights[c] = (matrix[r][c] == '1') ? heights[c] + 1 : 0;
            }
            // Evaluate the 1D histogram row configuration
            maxArea = Math.max(maxArea, calculateRowHistogramArea(heights));
        }
        return maxArea;
    }
    
    private static int calculateRowHistogramArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;
        
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        System.out.println("Maximal Rectangle Area: " + maximalRectangle(matrix)); // 6
    }
}