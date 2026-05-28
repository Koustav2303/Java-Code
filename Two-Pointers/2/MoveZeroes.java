import java.util.Arrays;

/**
 * PROBLEM: Move Zeroes
 * * Given an integer array nums, move all 0's to the end of it while maintaining 
 * the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 * * Example:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * * Approach:
 * Use a "slow" pointer to keep track of where the next non-zero element should go,
 * and a "fast" pointer to iterate through the array.
 */
public class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int insertPos = 0; // Slow pointer
        
        // Fast pointer 'i' finds all non-zero elements
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // Swap the non-zero element to the insert position
                int temp = nums[insertPos];
                nums[insertPos] = nums[i];
                nums[i] = temp;
                
                insertPos++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums)); // [1, 3, 12, 0, 0]
    }
}