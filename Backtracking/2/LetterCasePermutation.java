import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
    public static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(result, s.toCharArray(), 0);
        return result;
    }

    private static void backtrack(List<String> result, char[] chars, int index) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        if (Character.isLetter(chars[index])) {
            // Branch 1: lowercase
            chars[index] = Character.toLowerCase(chars[index]);
            backtrack(result, chars, index + 1);
            
            // Branch 2: uppercase
            chars[index] = Character.toUpperCase(chars[index]);
            backtrack(result, chars, index + 1);
        } else {
            // If it's a number, skip to the next index
            backtrack(result, chars, index + 1);
        }
    }

    public static void main(String[] args) {
        String s = "a1b2";
        System.out.println("Permutations: " + letterCasePermutation(s));
        // Output: [a1b2, a1B2, A1b2, A1B2]
    }
}