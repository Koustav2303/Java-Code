/**
 * PROBLEM: Count Primes in Ranges (Simulated Problem)
 * * Given multiple queries of ranges [L, R], answer how many prime numbers fall inside each range in O(1) time.
 * * Approach:
 * 1. Build a Sieve of Eratosthenes up to the maximum possible right bound.
 * 2. Build a Prefix Sum array over the boolean sieve.
 * 3. Answer queries in O(1) by subtracting prefix sums: prefix[R] - prefix[L - 1].
 */
public class RangePrimeQueries {
    private int[] prefixPrimes;

    public RangePrimeQueries(int maxN) {
        boolean[] isPrime = new boolean[maxN + 1];
        for (int i = 2; i <= maxN; i++) isPrime[i] = true;
        
        for (int i = 2; i * i <= maxN; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= maxN; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        prefixPrimes = new int[maxN + 1];
        for (int i = 1; i <= maxN; i++) {
            prefixPrimes[i] = prefixPrimes[i - 1] + (isPrime[i] ? 1 : 0);
        }
    }
    
    public int query(int left, int right) {
        if (left == 0) return prefixPrimes[right];
        return prefixPrimes[right] - prefixPrimes[left - 1];
    }

    public static void main(String[] args) {
        RangePrimeQueries rpq = new RangePrimeQueries(50);
        System.out.println("Primes between 10 and 20: " + rpq.query(10, 20)); // 4 (11, 13, 17, 19)
    }
}