import java.util.Arrays;

public class MaximumProductSubarray {
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = maxSoFar;

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            
            // If the current number is negative, it swaps the max and min values
            if (current < 0) {
                int temp = maxSoFar;
                maxSoFar = minSoFar;
                minSoFar = temp;
            }

            maxSoFar = Math.max(current, maxSoFar * current);
            minSoFar = Math.min(current, minSoFar * current);

            result = Math.max(result, maxSoFar);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4, -1};
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Maximum Product Subarray: " + maxProduct(nums)); 
        // Subarray [2, 3, -2, 4, -1] -> 2*3*-2*4*-1 = 48
    }
}