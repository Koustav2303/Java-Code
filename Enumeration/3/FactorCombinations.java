import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Factor Combinations
 * * Numbers can be regarded as product of its factors. For example, 8 = 2 x 2 x 2 = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * Elements in a combination must be in non-decreasing order to prevent duplicates.
 * * Strategy: Bounded Divisor Enumeration
 * Loop from a dynamic start factor up to the square root of the remaining value. 
 * If a number is perfectly divisible, accept that divisor, and recurse on the quotient.
 */
public class FactorCombinations {
    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(2, n, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, int n, List<Integer> current, List<List<Integer>> result) {
        // If we have existing factors in our path, the current remainder number 'n' 
        // itself is a valid closing factor option.
        if (current.size() > 0) {
            List<Integer> validCombination = new ArrayList<>(current);
            validCombination.add(n);
            result.add(validCombination);
        }

        // Only search up to the square root to enforce non-decreasing order constraints
        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                current.add(i);
                backtrack(i, n / i, current, result); // Recurse on quotient
                current.remove(current.size() - 1); // Backtrack
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Factor combinations for 12: " + getFactors(12));
        // [[2, 2, 3], [2, 6], [3, 4]]
    }
}