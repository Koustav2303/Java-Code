/**
 * PROBLEM: Count Primes
 * * Given an integer n, return the number of prime numbers that are strictly less than n.
 * * Example:
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * * Approach:
 * The classic 'Sieve of Eratosthenes' algorithm.
 * We create a boolean array to track primality. Starting from 2, if a number is prime, 
 * we 'cross out' (mark as false) all of its multiples. The numbers left marked as true are primes.
 */
public class CountPrimes {
    public static int countPrimes(int n) {
        if (n <= 2) return 0;
        
        // boolean arrays default to false in Java. 
        // We will invert the logic: false means prime, true means composite.
        boolean[] isComposite = new boolean[n];
        int count = 0;
        
        // We only need to check up to the square root of n
        for (int i = 2; i * i < n; i++) {
            if (!isComposite[i]) {
                // If it's prime, mark all its multiples as composite
                for (int j = i * i; j < n; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        
        // Count the numbers that are still false (prime)
        for (int i = 2; i < n; i++) {
            if (!isComposite[i]) {
                count++;
            }
        }
        
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Primes strictly less than 10: " + countPrimes(10)); // 4
        System.out.println("Primes strictly less than 30: " + countPrimes(30)); // 10
    }
}