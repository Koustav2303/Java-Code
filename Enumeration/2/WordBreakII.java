import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PROBLEM: Word Break II
 * * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where 
 * each word is a valid dictionary word. Return all such possible sentences in any order.
 * * Example:
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * * Complexity:
 * Time Complexity: O(2^N) sentence space combinations.
 * Space Complexity: O(N) recursion context memory stack.
 */
public class WordBreakII {
    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();
        backtrack(0, s, dict, new StringBuilder(), result);
        return result;
    }

    private static void backtrack(int start, String s, Set<String> dict, StringBuilder currentSentence, List<String> result) {
        if (start == s.length()) {
            result.add(currentSentence.toString().trim());
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            
            if (dict.contains(word)) {
                int previousLength = currentSentence.length();
                currentSentence.append(word).append(" ");
                
                backtrack(end, s, dict, currentSentence, result);
                
                currentSentence.setLength(previousLength); // Fast buffer rollback backtrack mechanism
            }
        }
    }

    public static void main(String[] args) {
        List<String> dict = List.of("cat", "cats", "and", "sand", "dog");
        System.out.println("Sentences compiled:\n" + wordBreak("catsanddog", dict));
    }
}