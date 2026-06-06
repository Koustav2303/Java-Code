/**
 * PROBLEM: Palindrome Number
 * * Given an integer x, return true if x is a palindrome, and false otherwise. 
 * Do not convert the integer to a string.
 * * Strategy: Mathematical Digit Reversal
 * Negative numbers can never be palindromes due to their leading minus sign. Similarly, non-zero numbers 
 * ending in 0 cannot be palindromes. Reverse the second half of the digits mathematically 
 * and compare it against the first half.
 * * Complexity:
 * Time Complexity: O(log_{10}(N))
 * Space Complexity: O(1)
 */
public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        // Base case exclusion filters
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversedHalf = 0;
        // Strip digits until you reach the midpoint of the integer
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + (x % 10);
            x /= 10;
        }

        // For even-length numbers: x == reversedHalf
        // For odd-length numbers: x == reversedHalf / 10 (discards the middle digit)
        return x == reversedHalf || x == reversedHalf / 10;
    }

    public static void main(String[] args) {
        System.out.println("Is 121 a palindrome? " + isPalindrome(121));   // true
        System.out.println("Is -121 a palindrome? " + isPalindrome(-121)); // false
    }
}