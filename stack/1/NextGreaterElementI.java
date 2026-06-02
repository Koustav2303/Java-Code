import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * PROBLEM: Next Greater Element I
 * * The next greater element of some element x in an array is the first greater element that is 
 * to the right of x in the same array.
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the 
 * next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 * * Strategy:
 * Run a monotonic stack over the larger array `nums2` from left to right. When a larger number pops an 
 * element off the stack, record that relationship inside a HashMap.
 * * Complexity:
 * Time Complexity: O(N + M) where N is nums1 length and M is nums2 length.
 * Space Complexity: O(M) for map and stack storage.
 */
public class NextGreaterElementI {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(); // maps element -> next greater element
        Stack<Integer> stack = new Stack<>();
        
        // Build map relationships via monotonic stack parsing on nums2
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println("Next Greater Elements: " + Arrays.toString(nextGreaterElement(nums1, nums2))); // [-1, 3, -1]
    }
}