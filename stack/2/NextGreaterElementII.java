import java.util.Arrays;
import java.util.Stack;

/**
 * PROBLEM: Next Greater Element II
 * * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), 
 * return the next greater number for every element in nums.
 * The next greater number of a number x is the first greater number to its traversing-order next 
 * in the array, which means we could search circularly to find its next greater number. 
 * If it doesn't exist, return -1 for this number.
 * * Strategy:
 * Simulate a circular array by iterating from `2 * n - 1` down to 0, using the modulo operator `i % n` 
 * to index into the array. We maintain a monotonic decreasing stack to resolve the next greater element.
 * * Complexity:
 * Time Complexity: O(N) because we traverse the array exactly twice.
 * Space Complexity: O(N) to store elements in the stack.
 */
public class NextGreaterElementII {
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>(); // Stores array indices
        
        // Loop through the array twice to simulate circular traversal
        for (int i = 0; i < 2 * n; i++) {
            int currentIdx = i % n;
            
            while (!stack.isEmpty() && nums[currentIdx] > nums[stack.peek()]) {
                int prevIdx = stack.pop();
                res[prevIdx] = nums[currentIdx];
            }
            
            // Only push indices from the first pass to avoid processing duplicates needlessly
            if (i < n) {
                stack.push(currentIdx);
            }
        }
        
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        System.out.println("Circular Next Greater: " + Arrays.toString(nextGreaterElements(nums))); // [2, -1, 2]
    }
}