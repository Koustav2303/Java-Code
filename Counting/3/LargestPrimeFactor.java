/**
 * PROBLEM: Largest Prime Factor
 * * Find the largest prime factor of a given number n.
 * * Approach:
 * Divide out the smallest prime (2) entirely.
 * Then iterate through odd numbers starting from 3 up to the square root of n, dividing them out.
 * Whatever remains of n at the end (if > 2) is the largest prime factor.
 */
public class LargestPrimeFactor {
    public static long largestPrimeFactor(long n) {
        long maxPrime = -1;
        
        // Extract all 2s
        while (n % 2 == 0) {
            maxPrime = 2;
            n /= 2;
        }
        
        // Extract odd primes up to the square root
        for (long i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                maxPrime = i;
                n /= i;
            }
        }
        
        // If n is still greater than 2, then n itself is the largest prime
        if (n > 2) {
            maxPrime = n;
        }
        
        return maxPrime;
    }

    public static void main(String[] args) {
        long n = 600851475143L; // Project Euler problem #3
        System.out.println("Largest prime factor of " + n + ": " + largestPrimeFactor(n)); // 6857
    }
}