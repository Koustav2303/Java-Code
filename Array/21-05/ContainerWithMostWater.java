import java.util.Arrays;

public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {
            // The height of the water is limited by the shorter wall
            int currentHeight = Math.min(height[left], height[right]);
            int currentWidth = right - left;
            maxWater = Math.max(maxWater, currentHeight * currentWidth);

            // Move the pointer of the shorter wall inward to try and find a taller one
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Wall heights: " + Arrays.toString(heights));
        System.out.println("Maximum water area: " + maxArea(heights));
    }
}