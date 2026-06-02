import java.util.Stack;

/**
 * PROBLEM: Score of Parentheses
 * * Given a balanced parentheses string s, return the score of the string.
 * The score of a balanced parentheses string is based on the following rules:
 * - "()" has score 1.
 * - AB has score A + B, where A and B are balanced parentheses strings.
 * - (A) has score 2 * A, where A is a balanced parentheses string.
 * * Example:
 * Input: s = "(())"
 * Output: 2
 * Input: s = "()()"
 * Output: 2
 * * Strategy:
 * Use a stack to hold scores of outer nested levels. The top of the stack always represents the 
 * current scope's score tracking. When we see '(', we push 0 to establish a new score tier.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class ScoreOfParentheses {
    public static int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Core root score layer
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); // Start a new nested layer
            } else {
                int currentScore = stack.pop();
                int outerScore = stack.pop();
                
                // If currentScore is 0, it means we had an immediate match "()", yielding a baseline score of 1.
                // Otherwise, it represents nested content score, which gets doubled: 2 * currentScore
                int calculatedValue = outerScore + Math.max(2 * currentScore, 1);
                stack.push(calculatedValue);
            }
        }
        
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println("Score of '(())': " + scoreOfParentheses("(())"));   // 2
        System.out.println("Score of '()()': " + scoreOfParentheses("()()"));   // 2
        System.out.println("Score of '(()(()))': " + scoreOfParentheses("(()(()))")); // 6
    }
}