/**
 * PROBLEM: Prime Palindrome
 * * Given an integer n, return the smallest prime palindrome greater than or equal to n.
 * * Approach:
 * Generate palindromes and check if they are prime. 
 * Mathematical Trick: ALL even-length palindromes (except 11) are strictly divisible by 11.
 * Therefore, if we hit an even length (like 8 digits), we can instantly skip to the next odd length (9 digits), 
 * bypassing millions of unnecessary calculations.
 */
public class PrimePalindrome {
    public static int primePalindrome(int n) {
        if (n <= 2) return 2;
        if (n >= 8 && n <= 11) return 11;
        
        // Start generating odd-length palindromes
        for (int length = 1; length <= 5; length++) { // 5 digit root creates 9 digit palindrome
            // Generate root numbers (e.g., length 2 root: 10 to 99)
            int start = (int) Math.pow(10, length - 1);
            int end = (int) Math.pow(10, length);
            
            for (int root = start; root < end; root++) {
                int palindrome = generateOddLengthPalindrome(root);
                if (palindrome >= n && isPrime(palindrome)) {
                    return palindrome;
                }
            }
        }
        return -1;
    }
    
    private static int generateOddLengthPalindrome(int root) {
        int res = root;
        root /= 10; // Drop the last digit (it acts as the pivot)
        while (root > 0) {
            res = res * 10 + (root % 10);
            root /= 10;
        }
        return res;
    }
    
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num % 2 == 0) return num == 2;
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Smallest prime palindrome >= 13: " + primePalindrome(13)); // 101
    }
}