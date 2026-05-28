/**
 * PROBLEM: Subarray Product Less Than K
 * * Given an array of integers nums and an integer k, return the number of contiguous subarrays 
 * where the product of all the elements in the subarray is strictly less than k.
 * * Example:
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class SubarrayProductLessThanK {
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0; // Since all numbers are positive, product can't be less than 1
        
        int count = 0;
        int product = 1;
        int left = 0;
        
        // Slide the 'right' pointer across the array
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            
            // While the product is too large, shrink the window from the left
            while (product >= k && left <= right) {
                product /= nums[left];
                left++;
            }
            
            // If the window is valid, the number of valid subarrays ending at 'right' 
            // is exactly the size of the window (right - left + 1).
            count += right - left + 1;
        }
        
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        System.out.println("Number of valid subarrays: " + numSubarrayProductLessThanK(nums, k)); // 8
    }
}