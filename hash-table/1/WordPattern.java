import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Word Pattern
 * * Given a pattern and a string s, find if s follows the same pattern. 
 * Follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 * * Strategy: Bijective Cross-Token Alignment Dictionary
 * Split the string into an array of individual words. Ensure the number of words matches the pattern length. 
 * Use a dual map architecture to cross-validate character tokens against word tokens. 
 * If a pattern character maps to multiple words, or multiple pattern characters map to the same word, 
 * the structure is invalid.
 */
public class WordPattern {
    public static boolean wordPattern(String pattern, String s) {
        String[] wordsTokenArray = s.split(" ");
        if (pattern.length() != wordsTokenArray.length) return false;

        Map<Character, String> patternToWordDictionary = new HashMap<>();
        Map<String, Character> wordToPatternDictionary = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char symbolToken = pattern.charAt(i);
            String wordToken = wordsTokenArray[i];

            // Validate forwards mapping consistency
            if (patternToWordDictionary.containsKey(symbolToken)) {
                if (!patternToWordDictionary.get(symbolToken).equals(wordToken)) return false;
            } else {
                patternToWordDictionary.put(symbolToken, wordToken);
            }

            // Validate backwards mapping consistency
            if (wordToPatternDictionary.containsKey(wordToken)) {
                if (wordToPatternDictionary.get(wordToken) != symbolToken) return false;
            } else {
                wordToPatternDictionary.put(wordToken, symbolToken);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba", text = "dog cat cat dog";
        System.out.println("Does pattern structure map correctly? " + wordPattern(pattern, text)); // true
    }
}