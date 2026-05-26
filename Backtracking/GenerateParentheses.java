import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, StringBuilder currentStr, int open, int close, int max) {
        if (currentStr.length() == max * 2) {
            result.add(currentStr.toString());
            return;
        }

        if (open < max) {
            currentStr.append("(");
            backtrack(result, currentStr, open + 1, close, max);
            currentStr.deleteCharAt(currentStr.length() - 1);
        }
        if (close < open) {
            currentStr.append(")");
            backtrack(result, currentStr, open, close + 1, max);
            currentStr.deleteCharAt(currentStr.length() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println("Valid Parentheses combinations for " + n + " pairs:");
        System.out.println(generateParenthesis(n));
    }
}