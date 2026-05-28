/**
 * PROBLEM: Range Sum Query - Immutable
 * * Given an integer array nums, handle multiple queries of the following type:
 * Calculate the sum of the elements of nums between indices left and right inclusive.
 * * Implement the NumArray class:
 * - NumArray(int[] nums) Initializes the object with the integer array nums.
 * - int sumRange(int left, int right) Returns the sum of the elements of nums 
 * between indices left and right inclusive (i.e. nums[left] + ... + nums[right]).
 * * Example:
 * Input:
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output:
 * [null, 1, -1, -3]
 * * Approach:
 * Build a prefix sum array where prefix[i] stores the sum of all elements up to i-1.
 * This allows us to answer any sumRange query in strictly O(1) time.
 */
public class RangeSumQuery {
    private int[] prefixSums;

    public RangeSumQuery(int[] nums) {
        // We make the prefix array 1 element larger to easily handle left == 0
        prefixSums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        // The sum from left to right is the prefix sum up to right + 1 
        // minus the prefix sum up to left.
        return prefixSums[right + 1] - prefixSums[left];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        RangeSumQuery obj = new RangeSumQuery(nums);
        System.out.println("Sum [0, 2]: " + obj.sumRange(0, 2)); // 1
        System.out.println("Sum [2, 5]: " + obj.sumRange(2, 5)); // -1
    }
}