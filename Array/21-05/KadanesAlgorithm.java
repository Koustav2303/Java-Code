import java.util.Arrays;

public class KadanesAlgorithm {
    public static int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currentMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Do we start a new subarray at nums[i], or add it to the current one?
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            // Update the overall maximum
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] numbers = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Maximum Subarray Sum: " + maxSubArray(numbers)); // Should be 6 (from [4,-1,2,1])
    }
}