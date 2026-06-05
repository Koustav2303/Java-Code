import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Primitive Roots
 * * Find the smallest primitive root modulo a given prime number p. A number g is a primitive root 
 * if its sequential powers modulo p generate every unique integer from 1 up to p - 1.
 * * Strategy: Prime Factorization Order Sieve
 * The multiplicative order of a primitive root must equal $\phi(p) = p - 1$. 
 * Find all unique prime factors of $p - 1$. A candidate $g$ is a primitive root if and only if:
 * $$g^{(p-1)/q} \not\equiv 1 \pmod p$$
 * for every distinct prime factor $q$ of $p - 1$.
 */
public class PrimitiveRoots {
    private static long modPow(long base, long exp, long mod) {
        long res = 1; base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    public static int findSmallestPrimitiveRoot(int p) {
        if (p <= 1) return -1;
        if (p == 2) return 1;

        int phi = p - 1;
        List<Integer> primeFactors = new ArrayList<>();
        int n = phi;
        
        // Find distinct prime factors of p - 1
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                primeFactors.add(i);
                while (n % i == 0) n /= i;
            }
        }
        if (n > 1) primeFactors.add(n);

        // Test candidates sequentially starting from 2
        for (int g = 2; g <= p; g++) {
            boolean isPrimitive = true;
            for (int factor : primeFactors) {
                if (modPow(g, phi / factor, p) == 1) {
                    isPrimitive = false;
                    break; // Order is smaller than phi, reject candidate
                }
            }
            if (isPrimitive) return g;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Smallest primitive root mod 11: " + findSmallestPrimitiveRoot(11)); // 2
    }
}