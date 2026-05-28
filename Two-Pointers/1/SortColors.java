import java.util.Arrays;

/**
 * PROBLEM: Sort Colors
 * * Given an array nums with n objects colored red, white, or blue, sort them in-place so that 
 * objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 * * Example:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 */
public class SortColors {
    public static void sortColors(int[] nums) {
        int low = 0;      // Boundary for 0s
        int mid = 0;      // Current element being evaluated
        int high = nums.length - 1; // Boundary for 2s
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap mid and low, increment both
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // 1 is in the correct middle section, just move forward
                mid++;
            } else { // nums[mid] == 2
                // Swap mid and high, decrement high. We do NOT increment mid because 
                // the swapped element from high needs to be evaluated.
                swap(nums, mid, high);
                high--;
            }
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println("Sorted colors: " + Arrays.toString(nums)); // [0, 0, 1, 1, 2, 2]
    }
}