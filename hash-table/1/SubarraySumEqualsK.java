import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Subarray Sum Equals K
 * * Given an array of integers nums and an integer k, return the total number of subarrays 
 * whose sum equals to k. A subarray is a contiguous non-empty sequence of elements within an array.
 * * Strategy: Prefix Sum Multiplicity Frequency Map
 * Maintain a running total of the prefix sum. If a sub-segment sums to $k$, then the difference 
 * between the current prefix sum and $k$ must match a previous prefix sum: `currentPrefixSum - k = targetPriorPrefixSum`. 
 * Store the frequencies of all previous prefix sums inside a HashMap to look up matches in constant time.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class SubarraySumEqualsK {
    public static int subarraySum(int[] nums, int k) {
        int matchingSubarraysCount = 0;
        int runningPrefixSum = 0;
        
        Map<Integer, Integer> prefixSumMultiplicityMap = new HashMap<>();
        prefixSumMultiplicityMap.put(0, 1); // Base condition: an empty prefix sum has a value of 0 and count of 1

        for (int num : nums) {
            runningPrefixSum += num;
            int targetedDifferenceComplement = runningPrefixSum - k;

            // If the complement exists, it confirms the existence of prior valid sub-segments
            if (prefixSumMultiplicityMap.containsKey(targetedDifferenceComplement)) {
                matchingSubarraysCount += prefixSumMultiplicityMap.get(targetedDifferenceComplement);
            }

            prefixSumMultiplicityMap.put(runningPrefixSum, prefixSumMultiplicityMap.getOrDefault(runningPrefixSum, 0) + 1);
        }
        return matchingSubarraysCount;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        System.out.println("Total sub-segments matching target value 2: " + subarraySum(nums, 2)); // 2
    }
}