/**
 * PROBLEM: Super Pow
 * * Calculate a^b % 1337 where b is a massive positive integer passed as an array of digits.
 * * Strategy: Recursive Base-10 Scaling
 * Apply properties of modular arithmetic to handle the digit array. 
 * Notice that: a^[1,2,3] = a^123 = (a^12)^10 * a^3. 
 * We can solve this recursively by peeling off the last digit, calculating its power, 
 * and processing the remaining digits scaled to the 10th power.
 */
public class SuperPow {
    private static final int MOD = 1337;

    private static int modPow(int base, int exp) {
        base %= MOD;
        int res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    public static int superPow(int a, int[] b) {
        return superPowHelper(a, b, b.length - 1);
    }

    private static int superPowHelper(int a, int[] b, int lastIndex) {
        if (lastIndex < 0) return 1;

        int lastDigit = b[lastIndex];
        // Peel off the last digit and recursively evaluate the rest
        int part1 = modPow(a, lastDigit);
        int part2 = modPow(superPowHelper(a, b, lastIndex - 1), 10);

        return (part1 * part2) % MOD;
    }

    public static void main(String[] args) {
        int[] b = {1, 0}; // b = 10
        System.out.println("Result: " + superPow(2, b)); // (2^10) % 1337 = 1024 % 1337 = 1024
    }
}