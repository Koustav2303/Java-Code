import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Expression Add Operators
 * * Given a string num that contains only digits and an integer target, return all possibilities to insert 
 * the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 * * Strategy: Complex Expression History Evaluation
 * Since multiplication `*` has a higher precedence than `+` or `-`, we must track the `previousValue` in our state. 
 * If a multiplication operator arrives, we roll back the prior operation via mathematical adjustments: 
 * `currentEvaluation - previousValue + (previousValue * currentDigit)`.
 */
public class ExpressionAddOperators {
    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.isEmpty()) return result;
        backtrack(0, "", 0, 0, num, target, result);
        return result;
    }

    private static void backtrack(int index, String path, long eval, long prev, String num, int target, List<String> result) {
        if (index == num.length()) {
            if (eval == target) result.add(path);
            return;
        }

        for (int i = index; i < num.length(); i++) {
            // Prune numbers with invalid leading zeros (e.g., "05")
            if (i > index && num.charAt(index) == '0') break;

            long currentVal = Long.parseLong(num.substring(index, i + 1));

            if (index == 0) {
                // First element cannot have a preceding operator prefix
                backtrack(i + 1, path + currentVal, currentVal, currentVal, num, target, result);
            } else {
                // Choice 1: Plus operator insertion path
                backtrack(i + 1, path + "+" + currentVal, eval + currentVal, currentVal, num, target, result);

                // Choice 2: Minus operator insertion path
                backtrack(i + 1, path + "-" + currentVal, eval - currentVal, -currentVal, num, target, result);

                // Choice 3: Multiply operator insertion path (Requires prior state rollback)
                backtrack(i + 1, path + "*" + currentVal, eval - prev + (prev * currentVal), prev * currentVal, num, target, result);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Valid equation variants for target 5: " + addOperators("123", 5)); // [1*2+3]
    }
}