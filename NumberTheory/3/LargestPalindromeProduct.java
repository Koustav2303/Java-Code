/**
 * PROBLEM: Largest Palindrome Product
 * * Given an integer n, return the largest palindrome made from the product of two n-digit numbers. 
 * Since the answer can be very large, return it modulo 1337.
 * * Strategy: Upper-Bound Palindrome Sifting
 * Calculate the maximum possible $n$-digit factor ($10^n - 1$). Generate palindrome candidates 
 * in descending order by mirroring the numerical upper half. For each candidate, verify if it can be 
 * factored into two $n$-digit components.
 */
public class LargestPalindromeProduct {
    public static int largestPalindrome(int n) {
        if (n == 1) return 9; // Core single-digit identity case

        int upperBound = (int) Math.pow(10, n) - 1;
        int lowerBound = upperBound / 10;

        // Generate palindrome upper halves in descending order
        for (long i = upperBound; i > lowerBound; i--) {
            long palindrome = createPalindrome(i);

            // Test if this candidate can be factored into two n-digit numbers
            for (long factor = upperBound; factor * factor >= palindrome; factor--) {
                if (palindrome % factor == 0) {
                    long secondFactor = palindrome / factor;
                    if (secondFactor > lowerBound) {
                        return (int) (palindrome % 1337);
                    }
                }
            }
        }
        return -1;
    }

    private static long createPalindrome(long half) {
        StringBuilder sb = new StringBuilder(String.valueOf(half));
        String reversed = sb.reverse().toString();
        return Long.parseLong(half + reversed);
    }

    public static void main(String[] args) {
        System.out.println("Largest palindrome product mod 1337 (n=2): " + largestPalindrome(2)); // 987 (99 * 91 = 9009 % 1337 = 987)
    }
}