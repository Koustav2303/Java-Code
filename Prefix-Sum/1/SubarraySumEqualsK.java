import java.util.HashMap;

/**
 * PROBLEM: Subarray Sum Equals K
 * * Given an array of integers nums and an integer k, return the total number of 
 * continuous subarrays whose sum equals to k.
 * * Example:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * * Approach:
 * We use a running prefix sum and a HashMap.
 * If at index `i`, the `runningSum` is `X`, and we are looking for a subarray summing to `k`,
 * we check if a previous prefix sum of exactly `X - k` exists in our map.
 * The map stores { PrefixSum : Frequency }.
 */
public class SubarraySumEqualsK {
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int runningSum = 0;
        
        // Map stores the frequency of each prefix sum we encounter
        HashMap<Integer, Integer> sumFreq = new HashMap<>();
        // Base case: A prefix sum of 0 has occurred exactly 1 time (before the array starts)
        sumFreq.put(0, 1);
        
        for (int num : nums) {
            runningSum += num;
            
            // If runningSum - k exists, it means a subarray ending here sums to k
            int targetPrefix = runningSum - k;
            if (sumFreq.containsKey(targetPrefix)) {
                count += sumFreq.get(targetPrefix);
            }
            
            // Record the current running sum in the map
            sumFreq.put(runningSum, sumFreq.getOrDefault(runningSum, 0) + 1);
        }
        
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, -3, 1, 1, 1, 4, -2, -2};
        int k = 3;
        System.out.println("Total subarrays summing to " + k + ": " + subarraySum(nums, k)); // 5
    }
}