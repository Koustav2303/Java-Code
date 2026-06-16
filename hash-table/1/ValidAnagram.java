/**
 * PROBLEM: Valid Anagram
 * * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * * Strategy: Symmetric Increment-Decrement Bucket Hash
 * If string lengths differ, they cannot be anagrams. Use a fixed-size integer array to count character frequencies. 
 * Loop through the strings, incrementing the count for characters in `s` and decrementing the count for characters in `t`. 
 * If the strings are anagrams, every bucket in the array will return to exactly 0.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1) matching constant 26 lowercase character slots footprints.
 */
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] alphabeticBucketCountersTable = new int[26];

        for (int i = 0; i < s.length(); i++) {
            alphabeticBucketCountersTable[s.charAt(i) - 'a']++;
            alphabeticBucketCountersTable[t.charAt(i) - 'a']--;
        }

        // Verify that every bucket returned to zero
        for (int balanceCount : alphabeticBucketCountersTable) {
            if (balanceCount != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Are 'anagram' and 'nagaram' matches? " + isAnagram("anagram", "nagaram")); // true
        System.out.println("Are 'rat' and 'car' matches? " + isAnagram("rat", "car"));         // false
    }
}