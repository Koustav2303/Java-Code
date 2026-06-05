/**
 * PROBLEM: Super Ugly Number
 * * A super ugly number is a positive integer whose prime factors are limited to a given prime pool array.
 * Given an integer n and an array of primes, return the nth super ugly number.
 * * Strategy: Multi-Pointer DP Sieve
 * Generalize the Ugly Number II pattern. Maintain a dynamic programming array `dp` to store 
 * generated super ugly numbers, and a `pointer` array to track indices within the `dp` table for each prime. 
 * Calculate the next minimum multiple across all prime options to append to the list.
 */
public class SuperUglyNumber {
    public static int nthSuperUglyNumber(int n, int[] primes) {
        long[] dp = new long[n];
        dp[0] = 1; // 1 is the default baseline super ugly number
        
        int k = primes.length;
        int[] pointers = new int[k];
        
        for (int i = 1; i < n; i++) {
            long minNext = Long.MAX_VALUE;
            
            // Step 1: Find the next smallest multiple candidate
            for (int j = 0; j < k; j++) {
                long candidate = dp[pointers[j]] * primes[j];
                minNext = Math.min(minNext, candidate);
            }
            
            dp[i] = minNext;
            
            // Step 2: Advance pointers for all primes that produced the minimum value to avoid duplicate states
            for (int j = 0; j < k; j++) {
                if (dp[pointers[j]] * primes[j] == minNext) {
                    pointers[j]++;
                }
            }
        }
        
        return (int) dp[n - 1];
    }

    public static void main(String[] args) {
        int[] primes = {2, 7, 13, 19};
        System.out.println("12th Super Ugly Number: " + nthSuperUglyNumber(12, primes)); // 32
    }
}