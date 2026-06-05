/**
 * PROBLEM: Euler's Criterion
 * * Given an integer a and a prime number p, determine if a is a quadratic residue modulo p. 
 * This means verifying if there exists an integer x such that $x^2 \equiv a \pmod p$.
 * * Strategy: Fermat-Euler Exponentiation
 * According to Euler's Criterion, a number $a$ is a quadratic residue mod an odd prime $p$ if and only if:
 * $$a^{(p-1)/2} \equiv 1 \pmod p$$
 * If the expression evaluates to $p - 1$ (equivalent to $-1$), it is a non-residue. 
 * Compute the value using fast modular binary exponentiation.
 */
public class EulerCriterion {
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

    public static boolean isQuadraticResidue(int a, int p) {
        if (a % p == 0) return true; // 0 is trivially a residue
        
        // Calculate the modular criterion exponent value
        long result = modPow(a, (p - 1) / 2, p);
        
        return result == 1;
    }

    public static void main(String[] args) {
        System.out.println("Is 4 a residue mod 7? " + isQuadraticResidue(4, 7)); // true (2^2 % 7 = 4)
        System.out.println("Is 3 a residue mod 7? " + isQuadraticResidue(3, 7)); // false
    }
}