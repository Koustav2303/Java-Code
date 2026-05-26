import java.util.ArrayList;
import java.util.List;

public class PhoneLetterCombinations {
    private static final String[] KEYPAD = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        
        backtrack(result, digits, new StringBuilder(), 0);
        return result;
    }

    private static void backtrack(List<String> result, String digits, StringBuilder currentStr, int index) {
        if (index == digits.length()) {
            result.add(currentStr.toString());
            return;
        }
        
        // Get the letters corresponding to the current digit
        String letters = KEYPAD[digits.charAt(index) - '0'];
        
        for (char c : letters.toCharArray()) {
            currentStr.append(c);
            backtrack(result, digits, currentStr, index + 1);
            currentStr.deleteCharAt(currentStr.length() - 1);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        System.out.println("Digits pressed: " + digits);
        System.out.println("Combinations: " + letterCombinations(digits));
    }
}