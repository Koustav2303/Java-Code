import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Letter Case Permutation
 * * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create. Return the output in any order.
 * * Example:
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * * Strategy: Traversal Decision Forking
 * If the current character is a digit, proceed straight forward. If it is a character, execute a 
 * two-fork recursion path: one keeping it lowercase and one flipping it to uppercase.
 */
public class LetterCasePermutation {
    public static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(0, s.toCharArray(), result);
        return result;
    }

    private static void backtrack(int index, char[] chars, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        // If it's a digit, skip mutation logic and move directly forward
        if (Character.isDigit(chars[index])) {
            backtrack(index + 1, chars, result);
        } else {
            // Choice 1: Lowercase branch execution path
            chars[index] = Character.toLowerCase(chars[index]);
            backtrack(index + 1, chars, result);

            // Choice 2: Uppercase branch execution path
            chars[index] = Character.toUpperCase(chars[index]);
            backtrack(index + 1, chars, result);
        }
    }

    public static void main(String[] args) {
        System.out.println("Letter Case Permutations: " + letterCasePermutation("a1b2"));
    }
}