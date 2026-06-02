import java.util.Stack;

/**
 * PROBLEM: Evaluate Reverse Polish Notation
 * * You are given an array of strings tokens that represents an arithmetic expression in a 
 * Reverse Polish Notation (Postfix Expression).
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * Valid operators are '+', '-', '*', and '/'.
 * * Example:
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * * Complexity:
 * Time Complexity: O(N) where N is the number of tokens.
 * Space Complexity: O(N) to store operands.
 */
public class EvaluateRPN {
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if ("+".equals(token)) {
                stack.push(stack.pop() + stack.pop());
            } else if ("-".equals(token)) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b); // Notice order: a was popped second, so it came first in the expression
            } else if ("*".equals(token)) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(token)) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] tokens = {"4", "13", "5", "/", "+"};
        System.out.println("Expression Evaluation: " + evalRPN(tokens)); // 4 + (13 / 5) = 6
    }
}