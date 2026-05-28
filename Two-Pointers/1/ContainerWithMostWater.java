/**
 * PROBLEM: Container With Most Water
 * * You are given an integer array height of length n. There are n vertical lines drawn such that 
 * the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 * * Example:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The vertical lines are drawn at indices 1 and 8 (values 8 and 7). 
 * Distance = 7. Min height = 7. Area = 7 * 7 = 49.
 */
public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            // Area = width * minimum height of the two lines
            int currentWidth = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int currentArea = currentWidth * currentHeight;
            
            maxArea = Math.max(maxArea, currentArea);
            
            // Always move the pointer that points to the shorter line to potentially find a taller one
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Maximum water area: " + maxArea(height)); // 49
    }
}