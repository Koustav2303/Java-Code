import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROBLEM: Combination Sum II
 * * Given a collection of candidate numbers (candidates) and a target number (target), find all unique 
 * combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination. Note: The solution set must not contain duplicate combinations.
 * * Example:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
 */
public class CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(0, candidates, target, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, int[] candidates, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) break; // Prune values exceeding target
            if (i > start && candidates[i] == candidates[i - 1]) continue; // Skip identical choices

            current.add(candidates[i]);
            // Pass 'i + 1' to guarantee unique instance consumption
            backtrack(i + 1, candidates, target - candidates[i], current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        System.out.println("Combination Sum II paths: " + combinationSum2(candidates, 8));
    }
}