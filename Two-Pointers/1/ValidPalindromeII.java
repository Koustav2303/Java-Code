/**
 * PROBLEM: Valid Palindrome II
 * * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 * * Example:
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 */
public class ValidPalindromeII {
    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // When a mismatch is found, we have two options:
            // 1. Delete the character at 'left' (skip left)
            // 2. Delete the character at 'right' (skip right)
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        
        return true; // Was already a palindrome
    }
    
    // Helper function to check if a specific substring is a pure palindrome
    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abca";
        System.out.println("Is valid palindrome II? " + validPalindrome(s)); // true
    }
}