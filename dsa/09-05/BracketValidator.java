import java.util.Stack;

public class BracketValidator {
    public static void main(String[] args) {
        String exp = "{[()]}";
        System.out.println("Balanced: " + check(exp));
    }

    public static boolean check(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') st.push(c);
            else {
                if (st.isEmpty()) return false;
                char top = st.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) return false;
            }
        }
        return st.isEmpty();
    }
}