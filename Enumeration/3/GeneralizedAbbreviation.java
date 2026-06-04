import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Generalized Abbreviation
 * * Write a function to generate the generalized abbreviations of a word.
 * * Example:
 * Input: word = "word"
 * Output: ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "3d", "w3", "1o2", "2r1", "4"]
 * * Strategy: Keep track of an active abbreviation count.
 * At each index, we have two options:
 * 1. Abbreviate the current character (increment count, move to next).
 * 2. Keep the character explicitly (append accumulated count if > 0, then append character, reset count to 0).
 */
public class GeneralizedAbbreviation {
    public static List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        backtrack(0, word, new StringBuilder(), 0, result);
        return result;
    }

    private static void backtrack(int index, String word, StringBuilder sb, int count, List<String> result) {
        int len = sb.length();
        if (index == word.length()) {
            if (count > 0) sb.append(count);
            result.add(sb.toString());
            sb.setLength(len); // Reset buffer state
            return;
        }

        // Choice 1: Abbreviate the current character (increase running count placeholder)
        backtrack(index + 1, word, sb, count + 1, result);

        // Choice 2: Maintain character explicitly. Must flush active counter first.
        if (count > 0) sb.append(count);
        sb.append(word.charAt(index));
        backtrack(index + 1, word, sb, 0, result);
        sb.setLength(len); // Rollback structural changes
    }

    public static void main(String[] args) {
        System.out.println("Abbreviations for 'word':\n" + generateAbbreviations("word"));
    }
}