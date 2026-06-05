import java.util.Random;

/**
 * PROBLEM: Miller-Rabin Primality Test
 * * Implement an ultra-fast, probabilistic primality test capable of evaluating massive numbers 
 * that would break traditional $\sqrt{n}$ trial division approaches.
 * * Strategy: Monotonic Base Probing
 * Write $n - 1$ as $2^s \cdot d$ by factoring out all powers of 2. Pick a random base $a$. 
 * Check if $a^d \equiv 1 \pmod n$ or $a^{2^r \cdot d} \equiv -1 \pmod n$ for some $0 \le r < s$. 
 * If neither condition holds, the number is definitely composite. Repeat for multiple bases to increase confidence.
 */
public class MillerRabinPrimality {
    private static long modPow(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    public static boolean isPrime(long n, int iterations) {
        if (n <= 1 || n == 4) return false;
        if (n <= 3) return true;
        if (n % 2 == 0) return false;

        // Factor out powers of 2 from n - 1
        long d = n - 1;
        while (d % 2 == 0) {
            d /= 2;
        }

        Random rand = new Random();
        for (int i = 0; i < iterations; i++) {
            // Choose a random base in range [2, n - 2]
            long a = 2 + (long)(rand.nextDouble() * (n - 4));
            if (!millerTest(d, n, a)) return false; // Definitely composite
        }
        return true; // Highly probable prime
    }

    private static boolean millerTest(long d, long n, long a) {
        long x = modPow(a, d, n);
        if (x == 1 || x == n - 1) return true;

        while (d != n - 1) {
            x = (x * x) % n;
            d *= 2;
            if (x == 1) return false;
            if (x == n - 1) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        long primeCandidate = 1000000007L;
        System.out.println("Is 1000000007 prime? " + isPrime(primeCandidate, 5)); // true
    }
}