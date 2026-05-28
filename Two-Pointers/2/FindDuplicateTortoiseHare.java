/**
 * PROBLEM: Find the Duplicate Number
 * * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 * * Example:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * * Approach: Treat the array values as pointers to the next index (Linked List Cycle).
 * Use Floyd's Cycle Detection (Fast and Slow pointers) to find the intersection, then 
 * use a second slow pointer to find the entrance to the cycle (the duplicate).
 */
public class FindDuplicateTortoiseHare {
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        
        // Phase 1: Find intersection point in the cycle
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        // Phase 2: Find the entrance to the cycle (which is the duplicate number)
        int slow2 = 0;
        while (slow != slow2) {
            slow = nums[slow];
            slow2 = nums[slow2];
        }
        
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println("Duplicate number: " + findDuplicate(nums)); // 2
    }
}