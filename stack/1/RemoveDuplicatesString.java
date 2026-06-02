import java.util.Stack;

/**
 * PROBLEM: Remove All Adjacent Duplicates In String
 * * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing 
 * two adjacent and equal letters and removing them.
 * We repeatedly make duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made.
 * * Example:
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation: "abbaca" -> "aaca" -> "ca"
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N) for stack building strings.
 */
public class RemoveDuplicatesString {
    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            // If current character matches the stack's top character, pop it (annihilation)
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        
        // Construct the remaining unique characters back into a string
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Result for 'abbaca': " + removeDuplicates("abbaca")); // "ca"
    }
}