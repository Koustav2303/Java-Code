import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagrams {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        // Arrays acting as HashMaps for character frequencies
        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Build frequency map for p, and the first window of s
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        // Slide the window across s
        for (int i = 0; i < s.length() - p.length(); i++) {
            // Check if current window matches
            if (Arrays.equals(pCount, sCount)) result.add(i);

            // Shift window: remove left char, add right char
            sCount[s.charAt(i) - 'a']--;
            sCount[s.charAt(i + p.length()) - 'a']++;
        }

        // Check the very last window
        if (Arrays.equals(pCount, sCount)) {
            result.add(s.length() - p.length());
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println("String: " + s + " | Target Anagram: " + p);
        System.out.println("Found anagrams starting at indices: " + findAnagrams(s, p));
    }
}