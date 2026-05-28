import java.util.HashMap;

/**
 * PROBLEM: Maximum Size Subarray Sum Equals k
 * * Given an integer array nums and an integer k, return the maximum length of a 
 * subarray that sums to k. If there is not one, return 0 instead.
 * * Example:
 * Input: nums = [1,-1,5,-2,3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * * Approach:
 * Extremely similar to "Subarray Sum Equals K" and "Contiguous Array".
 * We track the running prefix sum. If `runningSum - k` exists in our map, 
 * a valid subarray ends here. To maximize length, we only store the FIRST 
 * index a specific prefix sum was seen.
 */
public class MaxSizeSubarraySumK {
    public static int maxSubArrayLen(int[] nums, int k) {
        int maxLength = 0;
        long runningSum = 0;
        
        // Map stores { PrefixSum : Earliest Index seen }
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(0L, -1); // Base case
        
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            
            long targetPrefix = runningSum - k;
            if (map.containsKey(targetPrefix)) {
                maxLength = Math.max(maxLength, i - map.get(targetPrefix));
            }
            
            // We only add to the map if it doesn't exist to preserve the EARLIEST index
            if (!map.containsKey(runningSum)) {
                map.put(runningSum, i);
            }
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        System.out.println("Max subarray length summing to " + k + ": " + maxSubArrayLen(nums, k)); // 4
    }
}