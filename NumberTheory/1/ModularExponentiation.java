/**
 * PROBLEM: Modular Exponentiation
 * * Calculate (base^exp) % mod efficiently for large numbers without encountering arithmetic overflow.
 * * Strategy: Right-to-Left Binary Exponentiation
 * Instead of multiplying the base 'exp' times, break the exponent down into its binary representation. 
 * If the least significant bit of the exponent is 1, multiply the current base into the result. 
 * Continuously square the base and shift the exponent right by 1 bit at each step.
 * * Complexity:
 * Time Complexity: O(log(exp))
 * Space Complexity: O(1)
 */
public class ModularExponentiation {
    public static long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod; // Handle case where base is larger than mod

        while (exp > 0) {
            // If exp is odd, multiply base with the running result
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            // exp must be even now, shift bit and square the base base
            exp = exp >> 1;
            base = (base * base) % mod;
        }
        return res;
    }

    public static void main(String[] args) {
        long base = 2, exp = 10, mod = 1000;
        System.out.println("Result: " + power(base, exp, mod)); // (2^10) % 1000 = 1024 % 1000 = 24
    }
}