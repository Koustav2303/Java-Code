import java.util.Stack;

/**
 * PROBLEM: Basic Calculator II
 * * Given a string s which represents an expression, evaluate this expression and return its value.
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid. All intermediate results will be 
 * in the range of [-2^31, 2^31 - 1].
 * * Example:
 * Input: s = " 3+5 / 2 "
 * Output: 5
 * * Strategy:
 * Process numbers sequentially. Maintain the last operator seen.
 * If the last operator was '+' or '-', push the number (or negated number) to the stack.
 * If it was '*' or '/', pop the top value, evaluate it with the current number, and push it back.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class BasicCalculatorII {
    public static int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char operation = '+'; // Default starting sign
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }
            
            // If the character is an operator or we reached the end of the string
            if (!Character.isDigit(c) && c != ' ' || i == len - 1) {
                if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = c; // Update operation context
                currentNumber = 0;
            }
        }
        
        // Sum everything left in the stack
        int totalResult = 0;
        for (int val : stack) {
            totalResult += val;
        }
        return totalResult;
    }

    public static void main(String[] args) {
        System.out.println("Result of '3+5 / 2': " + calculate(" 3+5 / 2 ")); // 5
    }
}