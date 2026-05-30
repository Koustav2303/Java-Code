/**
 * PROBLEM: Valid Anagram
 * * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
 * typically using all the original letters exactly once.
 * * Example:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * * Approach:
 * Since the inputs are lowercase English letters, we can use a simple integer array of size 26 
 * instead of a HashMap. This provides a massive performance boost. We increment counts for 's' 
 * and decrement for 't'. If the array is all 0s at the end, they are anagrams.
 */
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] charCounts = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }
        
        for (int count : charCounts) {
            if (count != 0) return false;
        }
        
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is 'nagaram' an anagram of 'anagram'? " + isAnagram("anagram", "nagaram")); // true
    }
}