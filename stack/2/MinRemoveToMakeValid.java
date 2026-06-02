import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * PROBLEM: Minimum Remove to Make Valid Parentheses
 * * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) 
 * so that the resulting parentheses string is valid and return any valid string.
 * * Strategy:
 * Use a stack to track indices of opening parentheses. If we see a closing parenthesis, 
 * pop from the stack if it isn't empty. If the stack *is* empty, the closing parenthesis is invalid.
 * All remaining elements left in the stack at the end are unmatched opening parentheses.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class MinRemoveToMakeValid {
    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>(); // Track invalid indices
        Set<Integer> indicesToRemove = new HashSet<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    indicesToRemove.add(i); // Mark bad closing index
                } else {
                    stack.pop(); // Valid pair resolved
                }
            }
        }
        
        // Add remaining bad opening indices left over in the stack
        while (!stack.isEmpty()) {
            indicesToRemove.add(stack.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indicesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Valid match: " + minRemoveToMakeValid("lee(t(c)o)de)")); // "lee(t(c)o)de"
    }
}