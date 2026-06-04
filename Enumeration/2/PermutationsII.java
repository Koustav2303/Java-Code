import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROBLEM: Permutations II
 * * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 * * Example:
 * Input: nums = [1,1,2]
 * Output: [[1,1,2],[1,2,1],[2,1,1]]
 * * Strategy: Used-State Sibling Pruning
 * Sort the array. Maintain a boolean array to track used elements. Skip duplicates by checking if the 
 * current element matches the previous element and the previous element has NOT been used in the current path.
 */
public class PermutationsII {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            // If the current number is the same as the prior number, and the prior number was not 
            // consumed in this path yet, it means it was consumed and popped in a sibling path. Skip it!
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, used, current, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
            int[] nums = {1, 1, 2};
        System.out.println("Unique Permutations: " + permuteUnique(nums));
    }
}