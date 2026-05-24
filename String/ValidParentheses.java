import java.util.Stack;

public class ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c); // Push opening brackets
            } else {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop();
                // Check if the closing bracket matches the opening bracket type
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        // If the stack is empty, all brackets were matched
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String brackets = "({[]})";
        System.out.println("String: " + brackets);
        System.out.println("Are parentheses valid? " + isValid(brackets));
        
        String badBrackets = "([)]";
        System.out.println("String: " + badBrackets);
        System.out.println("Are parentheses valid? " + isValid(badBrackets));
    }
}