import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Permutations
 * * Given an array nums of distinct integers, return all the possible permutations. 
 * You can return the answer in any order.
 * * Example:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * * Strategy: Complete Permutation Enumeration
 * Use an internal state-tracking lookup table (boolean array) to record which elements are currently 
 * active in the path execution line, guaranteeing every position index is uniquely occupied.
 * * Complexity:
 * Time Complexity: O(N * N!) - There are N! permutations, each taking O(N) to construct.
 * Space Complexity: O(N) for state arrays and call frames.
 */
public class Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        // If the configuration size matches the source pool, an arrangement has been fully resolved
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(nums[i]);
                
                backtrack(nums, used, current, result); // Enumerate subsequent layers
                
                current.remove(current.size() - 1); // Undo choice
                used[i] = false; // Restore capacity
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("Permutation Enumeration:\n" + permute(nums));
    }
}