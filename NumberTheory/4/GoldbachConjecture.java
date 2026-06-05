import java.util.Arrays;

/**
 * PROBLEM: Goldbach's Conjecture
 * * Goldbach's conjecture states that every even integer greater than 2 can be expressed as the sum of two primes.
 * Given an even integer n, find the two prime numbers that sum up to n.
 * * Strategy: Linear Sieve Matching
 * Generate a boolean primality array up to $N$ using the Sieve of Eratosthenes. 
 * Then, use a two-pointer approach or match indices directly: check if both $i$ and $n - i$ are marked as prime.
 */
public class GoldbachConjecture {
    public static int[] getPrimePair(int n) {
        if (n <= 2 || n % 2 != 0) return new int[0];

        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) isPrime[j] = false;
            }
        }

        // Search for a complementary prime pair
        for (int i = 2; i <= n / 2; i++) {
            if (isPrime[i] && isPrime[n - i]) {
                return new int[]{i, n - i};
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int n = 28;
        System.out.println("Goldbach primes for 28: " + Arrays.toString(getPrimePair(n))); // [5, 23] or [11, 17]
    }
}