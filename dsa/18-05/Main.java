import java.util.*;

public class Main {
    public static String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();

        for (char digit : num.toCharArray()) {

            while (!stack.isEmpty() &&
                    k > 0 &&
                    stack.peek() > digit) {

                stack.pop();
                k--;
            }

            stack.push(digit);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();

        for (char c : stack) {
            sb.append(c);
        }

        while (sb.length() > 0 &&
                sb.charAt(0) == '0') {

            sb.deleteCharAt(0);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;

        System.out.println(removeKdigits(num, k));
    }
}