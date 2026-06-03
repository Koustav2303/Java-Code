import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Subsets (Power Set Enumeration)
 * * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * * Example:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * * Strategy: Cascading Combinatorial Enumeration
 * Use a recursive backtracking pattern. At each index, we choose to either include 
 * the current element in our subset or exclude it, expanding the binary state tree to depth N.
 * * Complexity:
 * Time Complexity: O(N * 2^N) - There are 2^N subsets, and copying each takes O(N).
 * Space Complexity: O(N) auxiliary space for the recursion stack.
 */
public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Enumerate the current valid configuration state by cloning it into the output list
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]); // Include element
            backtrack(i + 1, nums, current, result); // Recurse downstream
            current.remove(current.size() - 1); // Exclude element (Backtrack)
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("Power Set Enumeration: " + subsets(nums));
    }
}