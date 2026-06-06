import java.util.Arrays;

/**
 * PROBLEM: Count Primes
 * * Given an integer n, return the number of prime numbers that are strictly less than n.
 * * Strategy: Sieve of Eratosthenes
 * Create a boolean array initialized to true. Mark composites starting from 2. For each prime $i$, 
 * start marking off its multiples beginning at $i^2$. Loop up to $\sqrt{n}$ to optimize operations.
 * * Complexity:
 * Time Complexity: O(N log(log N))
 * Space Complexity: O(N)
 */
public class CountPrimes {
    public static int countPrimes(int n) {
        if (n <= 2) return 0;

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                // Mark multiples starting from i squared
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Primes less than 10: " + countPrimes(10)); // 4 (2, 3, 5, 7)
        System.out.println("Primes less than 30: " + countPrimes(30)); // 10
    }
}