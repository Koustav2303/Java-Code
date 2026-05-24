import java.util.HashMap;

public class WordPatternMatching {
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;

        HashMap<Character, String> charToWord = new HashMap<>();
        HashMap<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            // Check if mapping exists and is consistent
            if (charToWord.containsKey(c) && !charToWord.get(c).equals(word)) return false;
            if (wordToChar.containsKey(word) && wordToChar.get(word) != c) return false;

            // Create the bi-directional mapping
            charToWord.put(c, word);
            wordToChar.put(word, c);
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat dog";
        
        System.out.println("Pattern: " + pattern + " | String: " + s);
        System.out.println("Does it match? " + wordPattern(pattern, s));
    }
}