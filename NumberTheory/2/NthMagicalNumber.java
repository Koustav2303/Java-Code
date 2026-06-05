/**
 * PROBLEM: N-th Magical Number
 * * A positive integer is magical if it is divisible by either a or b. Given integers n, a, and b, 
 * return the nth magical number. Since the answer may be very large, return it modulo 10^9 + 7.
 * * Strategy: Binary Search with Inclusion-Exclusion Principle
 * The number of magical numbers less than or equal to a given value $X$ can be calculated using the 
 * set identity formula: $\lfloor X / a \rfloor + \lfloor X / b \rfloor - \lfloor X / \text{lcm}(a, b) \rfloor$.
 * Since this count is monotonically increasing, binary search the range $[1, \min(a,b) \cdot n]$ 
 * to find the exact boundary threshold.
 */
public class NthMagicalNumber {
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    public static int nthMagicalNumber(int n, int a, int b) {
        long lcmVal = lcm(a, b);
        long low = 1;
        long high = Math.min((long) a, (long) b) * n;
        long result = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            // Count total magical numbers present up to mid using Inclusion-Exclusion principle
            long count = (mid / a) + (mid / b) - (mid / lcmVal);

            if (count >= n) {
                result = mid; // Speculative bound match target
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        long MOD = 1_000_000_007;
        return (int) (result % MOD);
    }

    public static void main(String[] args) {
        System.out.println("4th magical number for a=2, b=3: " + nthMagicalNumber(4, 2, 3)); // 6 (2, 3, 4, 6)
    }
}