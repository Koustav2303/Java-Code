import java.util.Arrays;

/**
 * PROBLEM: Remove Duplicates from Sorted Array
 * * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place 
 * such that each unique element appears only once. The relative order of the elements should be kept the same. 
 * Then return the number of unique elements in nums.
 * * You must do this by modifying the input array in-place with O(1) extra memory.
 * * Example:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 */
public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        
        // insertPos points to the index where the next unique element should be written
        int insertPos = 1;
        
        // i iterates through the array looking for unique elements
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }
        
        return insertPos;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = removeDuplicates(nums);
        System.out.println("Number of unique elements: " + k); // 5
        System.out.println("Array after removal: " + Arrays.toString(Arrays.copyOf(nums, k)));
    }
}