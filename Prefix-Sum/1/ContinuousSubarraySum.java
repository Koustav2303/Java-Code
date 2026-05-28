import java.util.HashMap;

/**
 * PROBLEM: Continuous Subarray Sum
 * * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 * A good subarray is a subarray where:
 * - its length is at least two, and
 * - the sum of the elements of the subarray is a multiple of k.
 * * Example:
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 * * Approach:
 * We use the Modulo operator with Prefix Sums.
 * If `(PrefixSum_j % k) == (PrefixSum_i % k)`, then the sum of elements between 
 * `i` and `j` is a multiple of `k`.
 * We use a Map to store { PrefixSum % k : Index }.
 */
public class ContinuousSubarraySum {
    public static boolean checkSubarraySum(int[] nums, int k) {
        // Map stores { Remainder of PrefixSum % k : Earliest Index seen }
        HashMap<Integer, Integer> remainderMap = new HashMap<>();
        // Base case: a remainder of 0 exists at index -1
        remainderMap.put(0, -1);
        
        int runningSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            
            int remainder = runningSum % k;
            
            // In case of negative remainders in Java (if array had negative numbers, though this problem usually has positives)
            if (remainder < 0) {
                remainder += k;
            }
            
            if (remainderMap.containsKey(remainder)) {
                // Check if the subarray length is at least 2
                if (i - remainderMap.get(remainder) >= 2) {
                    return true;
                }
            } else {
                // Record the earliest index of this remainder
                remainderMap.put(remainder, i);
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        System.out.println("Has valid multiple of k? " + checkSubarraySum(nums, k)); // true
    }
}