import java.util.HashMap;

/**
 * PROBLEM: Contiguous Array
 * * Given a binary array nums, return the maximum length of a contiguous subarray 
 * with an equal number of 0 and 1.
 * * Example:
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * * Approach:
 * Treat 0 as -1 and 1 as 1. Keep a running prefix sum. 
 * If we encounter a prefix sum that we've seen before, it means the elements between 
 * that previous index and our current index sum exactly to 0 (meaning equal 0s and 1s).
 * Use a HashMap to store the FIRST time we see a particular prefix sum.
 */
public class ContiguousArray {
    public static int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // Base case: a prefix sum of 0 exists at index -1 before the array starts
        map.put(0, -1);
        
        int maxLength = 0;
        int runningSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // Convert 0 to -1 for the math to balance out
            runningSum += (nums[i] == 1 ? 1 : -1);
            
            if (map.containsKey(runningSum)) {
                // If we've seen this sum before, calculate the distance
                maxLength = Math.max(maxLength, i - map.get(runningSum));
            } else {
                // Only record the FIRST occurrence to maximize the length
                map.put(runningSum, i);
            }
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 0, 1, 1, 0};
        System.out.println("Max contiguous length: " + findMaxLength(nums)); // 6
    }
}