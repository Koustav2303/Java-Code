import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Combinations
 * * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * You may return the answer in any order.
 * * Example:
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * * Strategy: Bounded Selection Enumeration
 * Enumerate options sequentially while employing a pruning optimization check: if the remaining choices 
 * in our range are fewer than the remaining slots needed to satisfy size k, terminate that search line early.
 * * Complexity:
 * Time Complexity: O(k * C(n, k)) where C(n, k) is the binomial coefficient tracking total valid pairings.
 * Space Complexity: O(k) stack allocation frame.
 */
public class Combinations {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
        // Selection goal size met
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Optimization Pruning: Ensure pool capacity matches the required quota gap
        // (n - i + 1) represents remaining elements, (k - current.size()) represents elements needed
        for (int i = start; i <= n - (k - current.size()) + 1; i++) {
            current.add(i);
            backtrack(i + 1, n, k, current, result);
            current.remove(current.size() - 1); // Clean slate backtrack step
        }
    }

    public static void main(String[] args) {
        System.out.println("Combinatorics Choice Range [1,4] choose 2: " + combine(4, 2));
    }
}