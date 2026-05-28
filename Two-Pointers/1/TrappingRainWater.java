/**
 * PROBLEM: Trapping Rain Water
 * * Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 * compute how much water it can trap after raining.
 * * Example:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
 * In this case, 6 units of rain water are being trapped.
 */
public class TrappingRainWater {
    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int waterTrapped = 0;
        
        while (left < right) {
            // Update the maximum heights seen so far from both sides
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            
            // The water level depends on the shorter of the two maximum boundaries
            if (leftMax < rightMax) {
                waterTrapped += leftMax - height[left];
                left++;
            } else {
                waterTrapped += rightMax - height[right];
                right--;
            }
        }
        
        return waterTrapped;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Total trapped water: " + trap(height)); // 6
    }
}