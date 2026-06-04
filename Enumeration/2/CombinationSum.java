import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROBLEM: Combination Sum
 * * Given an array of distinct integers candidates and a target integer target, return a list of all 
 * unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 * The same number may be chosen from candidates an unlimited number of times. 
 * * Example:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * * Strategy: Unbounded Backpack Search
 * Sort the array to allow early termination. Pass the current index 'i' into the next recursive call 
 * rather than 'i + 1', which models the infinite availability of the elements.
 */
public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sorting allows immediate branch cutting when over target
        backtrack(0, candidates, target, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, int[] candidates, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Early pruning: Since array is sorted, if this candidate is too large, all remaining siblings are too large
            if (target - candidates[i] < 0) break;

            current.add(candidates[i]);
            // Maintain 'i' as the start index parameter to allow repeated element choices
            backtrack(i, candidates, target - candidates[i], current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        System.out.println("Combination Sum paths: " + combinationSum(candidates, 7));
    }
}