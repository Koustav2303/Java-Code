import java.util.Stack;

/**
 * PROBLEM: Decode String
 * * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets 
 * is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * * Example:
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * * Strategy: Dual Stacks
 * Maintain a `countStack` for multipliers and a `stringStack` to cache partial string states.
 * When encountering '[', push the current count and string builder context. When encountering ']', 
 * pop the context and run a multiplier loop.
 * * Complexity:
 * Time Complexity: O(Max(K) * N) where K is the multiplier value.
 * Space Complexity: O(N) to hold nested contexts.
 */
public class DecodeString {
    public static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0'); // Build multi-digit numbers
            } else if (c == '[') {
                countStack.push(k);
                stringStack.push(currentString);
                currentString = new StringBuilder(); // Reset context
                k = 0;
            } else if (c == ']') {
                StringBuilder decodedPattern = currentString;
                int repeatTimes = countStack.pop();
                currentString = stringStack.pop();
                
                while (repeatTimes-- > 0) {
                    currentString.append(decodedPattern);
                }
            } else {
                currentString.append(c);
            }
        }
        
        return currentString.toString();
    }

    public static void main(String[] args) {
        String test = "3[a2[c]]";
        System.out.println("Decoded pattern: " + decodeString(test)); // accaccacc
    }
}