import java.util.Stack;

/**
 * PROBLEM: Valid Parentheses
 * * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 * * Example:
 * Input: s = "()[]{}"
 * Output: true
 * * Complexity:
 * Time Complexity: O(N) where N is the length of the string.
 * Space Complexity: O(N) to store the characters in the stack.
 */
public class ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            // Push the corresponding closing bracket when an opening bracket is found
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                // If the stack is empty or the top element doesn't match, the string is invalid
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        
        // If the stack is empty, all brackets were matched perfectly
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String test1 = "()[]{}";
        String test2 = "([)]";
        System.out.println("Is '" + test1 + "' valid? " + isValid(test1)); // true
        System.out.println("Is '" + test2 + "' valid? " + isValid(test2)); // false
    }
}