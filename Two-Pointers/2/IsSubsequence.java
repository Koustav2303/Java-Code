/**
 * PROBLEM: Is Subsequence
 * * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string by deleting 
 * some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 * * Example:
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Explanation: 'a', 'b', and 'c' appear in 't' in the same order.
 */
public class IsSubsequence {
    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        
        int sPointer = 0;
        int tPointer = 0;
        
        while (tPointer < t.length()) {
            if (s.charAt(sPointer) == t.charAt(tPointer)) {
                sPointer++;
                // If we've found all characters of s, it is a subsequence
                if (sPointer == s.length()) return true;
            }
            tPointer++;
        }
        
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc")); // true
        System.out.println(isSubsequence("axc", "ahbgdc")); // false
    }
}