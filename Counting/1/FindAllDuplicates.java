import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Find All Duplicates in an Array
 * * Given an integer array nums of length n where all the integers of nums are in the range [1, n] 
 * and each integer appears once or twice, return an array of all the integers that appears twice.
 * * Constraint: You must write an algorithm that runs in O(n) time and uses only constant extra space.
 * * Example:
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 * * Approach:
 * Because the numbers are bound between 1 and N, we can use the array itself to track counts.
 * When evaluating `num`, we jump to index `num - 1` and negate the value there.
 * If we jump to an index and the value is ALREADY negative, it means we've seen this number before!
 */
public class FindAllDuplicates {
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Get the absolute value since it might have been negated already
            int index = Math.abs(nums[i]) - 1;
            
            // If the value at this index is already negative, we found a duplicate
            if (nums[index] < 0) {
                duplicates.add(index + 1);
            } else {
                // Otherwise, mark it as seen by negating it
                nums[index] = -nums[index];
            }
        }
        
        return duplicates;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Duplicates found: " + findDuplicates(nums)); // [2, 3]
    }
}