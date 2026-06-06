import java.util.Arrays;

/**
 * PROBLEM: Prime Arrangements
 * * Return the number of permutations of 1 to n so that prime numbers are at prime indices 
 * (1-indexed). Since the answer may be large, return it modulo 10^9 + 7.
 * * Strategy: Separated Sub-Factorial Multiplication
 * Count the total number of primes $P$ up to $n$ using a sieve. The remaining elements $NP = n - P$ 
 * are non-primes. The number of valid configurations is calculated by multiplying the permutations 
 * of primes by the permutations of non-primes: $P! \times NP! \pmod{10^9+7}$.
 */
public class PrimeArrangements {
    private static final long MOD = 1_000_000_007;

    public static int numPrimeArrangements(int n) {
        int primeCount = countPrimesUpTo(n);
        int nonPrimeCount = n - primeCount;

        long pFactorial = factorial(primeCount);
        long npFactorial = factorial(nonPrimeCount);

        return (int) ((pFactorial * npFactorial) % MOD);
    }

    private static int countPrimesUpTo(int n) {
        if (n < 2) return 0;
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

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

    private static long factorial(int num) {
        long fact = 1;
        for (int i = 2; i <= num; i++) {
            fact = (fact * i) % MOD;
        }
        return fact;
    }

    public static void main(String[] args) {
        System.out.println("Arrangements for n = 5: " + numPrimeArrangements(5)); // 12 -> Primes: {2,3,5}, Non-primes: {1,4} -> 3! * 2! = 12
    }
}