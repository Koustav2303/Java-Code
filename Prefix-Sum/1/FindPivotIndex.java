/**
 * PROBLEM: Find Pivot Index
 * * Given an array of integers nums, calculate the pivot index of this array.
 * The pivot index is the index where the sum of all the numbers strictly to the left 
 * of the index is equal to the sum of all the numbers strictly to the index's right.
 * If the index is on the left edge of the array, then the left sum is 0.
 * Return the leftmost pivot index. If no such index exists, return -1.
 * * Example:
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 * * Approach:
 * Get the total sum of the array first.
 * Iterate through the array keeping track of the `leftSum`.
 * The `rightSum` at any index `i` is simply `totalSum - leftSum - nums[i]`.
 */
public class FindPivotIndex {
    public static int pivotIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            
            if (leftSum == rightSum) {
                return i;
            }
            
            leftSum += nums[i];
        }
        
        return -1; // No pivot found
    }

    public static void main(String[] args) {
        int[] nums = {1, 7, 3, 6, 5, 6};
        System.out.println("Pivot index: " + pivotIndex(nums)); // 3
    }
}