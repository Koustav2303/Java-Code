import java.util.Stack;

/**
 * PROBLEM: Largest Rectangle in Histogram
 * * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, 
 * return the area of the largest rectangle in the histogram.
 * * Strategy: Monotonic Increasing Stack
 * We maintain a stack containing indices of heights sorted in an ascending order. 
 * When we see a bar shorter than the bar at stack.peek(), we can calculate the area of rectangles 
 * that have the popped bar as their shortest height. The width is calculated by subtracting indices.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class LargestRectangleHistogram {
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;
        
        for (int i = 0; i <= n; i++) {
            // Use a dummy height of 0 at index n to force flush the remaining elements left in the stack
            int currentHeight = (i == n) ? 0 : heights[i];
            
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                // If stack is empty, width spans from index 0 to i
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Max rectangle area: " + largestRectangleArea(heights)); // 10 (Heights 5 and 6 with width 2)
    }
}