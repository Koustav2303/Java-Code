import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Letter Combinations of a Phone Number
 * * Given a string containing digits from 2-9 inclusive, return all possible letter combinations 
 * that the number could represent. Return the answer in any order.
 * A mapping of digits to letters (just like on the telephone buttons) is provided below. 
 * Note that 1 does not map to any letters.
 * * Example:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * * Complexity:
 * Time Complexity: O(4^N) worst-case mapping matrix scales (e.g., digits 7 and 9 have 4 choices).
 * Space Complexity: O(N) map path array allocation.
 */
public class LetterCombinations {
    private static final String[] MAPPING = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        backtrack(0, digits, new StringBuilder(), result);
        return result;
    }

    private static void backtrack(int index, String digits, StringBuilder current, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = MAPPING[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(index + 1, digits, current, result); // Enumerate next keyboard digit
            current.deleteCharAt(current.length() - 1); // Backtrack choice string context
        }
    }

    public static void main(String[] args) {
        System.out.println("Phone Map Enumeration for '23': " + letterCombinations("23"));
    }
}