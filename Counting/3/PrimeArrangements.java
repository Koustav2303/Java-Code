/**
 * PROBLEM: Prime Arrangements
 * * Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed).
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 * * Example:
 * Input: n = 5
 * Output: 12
 * Explanation: Primes are 2, 3, 5. Non-primes are 1, 4. 
 * Primes must be at indices 2, 3, 5. Non-primes at 1, 4.
 * Math: (3!) * (2!) = 6 * 2 = 12.
 * * Approach:
 * Count the number of primes up to n. Let this be P. 
 * The non-primes will be n - P.
 * The answer is simply factorial(P) * factorial(n - P) % (10^9 + 7).
 */
public class PrimeArrangements {
    public static int numPrimeArrangements(int n) {
        int primes = countPrimes(n);
        int nonPrimes = n - primes;
        long MOD = 1_000_000_007;
        
        return (int) ((factorial(primes, MOD) * factorial(nonPrimes, MOD)) % MOD);
    }
    
    private static int countPrimes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) isPrime[i] = true;
        
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) isPrime[j] = false;
            }
        }
        
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }
    
    private static long factorial(int n, long mod) {
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res = (res * i) % mod;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Prime arrangements for n = 5: " + numPrimeArrangements(5)); // 12
    }
}