import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Two Sum
 * * Given an array of integers nums and an integer target, return indices of the two numbers 
 * such that they add up to target. You may assume that each input would have exactly one solution.
 * * Strategy: Single-Pass Look-Ahead Complement Memory
 * Iterate through the array. For each number, calculate its complement: `target - currentNumber`. 
 * Check if this complement exists in your HashMap. If it does, return the index pair immediately. 
 * If it doesn't, map the current number to its index and continue.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> valueToIndexLookupMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complementValue = target - nums[i];

            if (valueToIndexLookupMap.containsKey(complementValue)) {
                return new int[]{valueToIndexLookupMap.get(complementValue), i};
            }
            valueToIndexLookupMap.put(nums[i], i);
        }
        return new int[]{-1, -1}; // Fallback condition for invalid configurations
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        System.out.println("Matching key lookup indices output: " + Arrays.toString(twoSum(nums, 9))); // [0, 1]
    }
}