import java.util.HashMap;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Integer> charCounts = new HashMap<>();

        // Add counts for string 's'
        for (char c : s.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        // Subtract counts for string 't'
        for (char c : t.toCharArray()) {
            if (!charCounts.containsKey(c) || charCounts.get(c) == 0) {
                return false; // Found a character that doesn't match
            }
            charCounts.put(c, charCounts.get(c) - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        String word1 = "listen";
        String word2 = "silent";
        
        System.out.println("Word 1: " + word1);
        System.out.println("Word 2: " + word2);
        System.out.println("Are they anagrams? " + isAnagram(word1, word2));
    }
}