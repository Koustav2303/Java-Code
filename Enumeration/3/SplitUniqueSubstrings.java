import java.util.HashSet;
import java.util.Set;

/**
 * PROBLEM: Split a String Into the Max Number of Unique Substrings
 * * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 * You can split string s into any list of non-empty substrings, such that the concatenation of the substrings 
 * forms the original string.
 * * Example:
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One optimal split is ["a", "b", "ab", "c", "cc"].
 * * Strategy: Backtracking with Global Unique Filter
 * Slice string chunks progressively. If the string slice segment does not exist inside a tracking HashSet, 
 * register it, increment our step counter, and recursively parse forward.
 */
public class SplitUniqueSubstrings {
    public static int maxUniqueSplit(String s) {
        return backtrack(0, s, new HashSet<>());
    }

    private static int backtrack(int start, String s, Set<String> seen) {
        if (start == s.length()) return 0;

        int maxSplits = -1;
        for (int end = start + 1; end <= s.length(); end++) {
            String substring = s.substring(start, end);
            
            if (!seen.contains(substring)) {
                seen.add(substring); // Lock state
                
                int subSplits = backtrack(end, s, seen);
                if (subSplits != -1) {
                    maxSplits = Math.max(maxSplits, 1 + subSplits);
                }
                
                seen.remove(substring); // Unlock state (Backtrack)
            }
        }
        return maxSplits;
    }

    public static void main(String[] args) {
        System.out.println("Max Unique Substrings split count: " + maxUniqueSplit("ababccc")); // 5
    }
}