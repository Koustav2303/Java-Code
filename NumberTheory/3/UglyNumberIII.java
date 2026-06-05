/**
 * PROBLEM: Ugly Number III
 * * Given four integers n, a, b, and c, return the nth ugly number. 
 * Ugly numbers here are positive integers that are divisible by a, b, or c.
 * * Strategy: Inclusion-Exclusion Principle over Binary Search Range
 * The total count of multiples of $a$, $b$, or $c$ less than or equal to a value $X$ is given by 
 * the set identity principle:
 * $$\text{count} = \lfloor X / a \rfloor + \lfloor X / b \rfloor + \lfloor X / c \rfloor - \lfloor X / \text{lcm}(a,b) \rfloor - \lfloor X / \text{lcm}(b,c) \rfloor - \lfloor X / \text{lcm}(a,c) \rfloor + \lfloor X / \text{lcm}(a,b,c) \rfloor$$
 * Since the count function is monotonically increasing, use binary search to locate the exact 
 * matching numerical boundary.
 */
public class UglyNumberIII {
    public static int nthUglyNumber(int n, int a, int b, int c) {
        long low = 1, high = 2_000_000_000;
        long ab = lcm(a, b), bc = lcm(b, c), ac = lcm(a, c);
        long abc = lcm(ab, c);
        long result = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            // Apply Inclusion-Exclusion Principle
            long count = (mid / a) + (mid / b) + (mid / c) - (mid / ab) - (mid / bc) - (mid / ac) + (mid / abc);

            if (count >= n) {
                result = mid;
                high = mid - 1; // Try to contract the upper bound to find the minimal matching element
            } else {
                low = mid + 1;
            }
        }
        return (int) result;
    }

    private static long gcd(long x, long y) {
        while (y != 0) { long t = y; y = x % y; x = t; }
        return x;
    }

    private static long lcm(long x, long y) {
        return (x / gcd(x, y)) * y;
    }

    public static void main(String[] args) {
        System.out.println("4th multiple instance for 2, 3, 5: " + nthUglyNumber(4, 2, 3, 5)); // 5 (Multiples: 2, 3, 4, 5)
    }
}