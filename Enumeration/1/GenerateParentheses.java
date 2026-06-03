import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Generate Parentheses
 * * Given n pairs of parentheses, generate all combinations of well-formed parentheses.
 * * Example:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * * Strategy: Monotonic Counting Constraint Enumeration
 * Instead of generating all strings and testing validity, explicitly preserve structural syntax constraints:
 * 1. You can always add an open bracket '(' if openCount < n.
 * 2. You can only add a close bracket ')' if closeCount < openCount.
 * * Complexity:
 * Time Complexity: O(4^N / sqrt(N)) bounded by the Nth Catalan structural count distribution.
 * Space Complexity: O(N) recursion state frames.
 */
public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(0, 0, n, new StringBuilder(), result);
        return result;
    }

    private static void backtrack(int open, int close, int max, StringBuilder sb, List<String> result) {
        if (sb.length() == max * 2) {
            result.add(sb.toString());
            return;
        }

        // Rule 1: We can open branches safely up to the absolute pairs limit count
        if (open < max) {
            sb.append('(');
            backtrack(open + 1, close, max, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        // Rule 2: Balance checks ensure closures never compromise prior opened brackets
        if (close < open) {
            sb.append(')');
            backtrack(open, close + 1, max, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("Valid Bracket Structures (n=3):\n" + generateParenthesis(3));
    }
}