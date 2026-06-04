import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Combination Sum III
 * * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * - Only numbers 1 through 9 are used.
 * - Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain duplicate combinations.
 * * Example:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * * Strategy: Multi-Constraint Bounded Search
 * Limit the choice loop strictly from 1 to 9. Prune early if the current choice exceeds the remaining target, 
 * or if the path length exceeds k.
 */
public class CombinationSumIII {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, int k, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0 && current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        // Pruning: Stop exploration if path size is reached or target is violated
        if (current.size() == k || target < 0) return;

        for (int i = start; i <= 9; i++) {
            if (target - i < 0) break; // Prune all larger digits

            current.add(i);
            backtrack(i + 1, k, target - i, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        System.out.println("Valid combinations (k=3, n=7): " + combinationSum3(3, 7)); // [[1, 2, 4]]
    }
}