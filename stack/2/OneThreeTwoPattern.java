import java.util.Stack;

/**
 * PROBLEM: 132 Pattern
 * * Given an array of n integers nums, return true if there is a 132 pattern in the array.
 * A 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that 
 * i < j < k and nums[i] < nums[k] < nums[j].
 * * Strategy: Monotonic Decreasing Stack from Right to Left
 * We parse backward. We maintain a variable `s3` (representing the '2' in the 132 pattern). 
 * The stack holds values that are candidates for `s2` (the '3' in the pattern). 
 * When a new number is larger than elements in the stack, we update `s3` to be the maximum popped element. 
 * If we ever find an element smaller than `s3`, a 132 pattern is found.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class OneThreeTwoPattern {
    public static boolean find132pattern(int[] nums) {
        int s3 = Integer.MIN_VALUE; // Represents the '2' in the 132 pattern
        Stack<Integer> stack = new Stack<>(); // Candidates for '3' (s2)
        
        // Traverse backwards
        for (int i = nums.length - 1; i >= 0; i--) {
            // If we find an element smaller than s3, we found our '1' (s1 < s3)
            if (nums[i] < s3) {
                return true;
            }
            
            // Maintain a monotonic decreasing stack. The values popped are smaller 
            // than the current number, making them candidates for s3.
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                s3 = stack.pop(); // Maximize s3
            }
            
            stack.push(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2};
        System.out.println("Contains 132 pattern? " + find132pattern(nums)); // true (Subsequence: 1, 4, 2)
    }
}