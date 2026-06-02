import java.util.Stack;

/**
 * PROBLEM: Remove K Digits
 * * Given string num representing a non-negative integer num, and an integer k, return the smallest 
 * possible integer after removing k digits from num.
 * * Example:
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number "1219" which is the smallest.
 * * Strategy: Greedy Monotonic Increasing Stack
 * To make a number smaller, we want smaller digits at higher place values (left to right).
 * Traverse the digits and maintain a monotonic increasing stack. If the current digit is smaller 
 * than the top of the stack, pop the stack and decrement k.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class RemoveKDigits {
    public static String removeKdigits(String num, int k) {
        if (k >= num.length()) return "0";
        
        Stack<Character> stack = new Stack<>();
        for (char digit : num.toCharArray()) {
            // While we have removals left and the current digit is smaller than the top element
            while (k > 0 && !stack.isEmpty() && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }
        
        // If we still have removals left (e.g., input was already sorted like "12345"), remove from the end
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        // Reconstruct the string
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        // Remove leading zeros using a loop
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Smallest value: " + removeKdigits("1432219", 3)); // "1219"
        System.out.println("Smallest value: " + removeKdigits("10200", 1));   // "200"
    }
}