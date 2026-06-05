/**
 * PROBLEM: Integer Break
 * * Given an integer n, break it into the sum of k positive integers, where k >= 2, 
 * and maximize the product of those integers. Return the maximum product you can obtain.
 * * Strategy: Base-3 Partition Optimization
 * Mathematically, maximizing a product of components summing to $N$ involves breaking the number into 
 * equal parts close to the natural base $e \approx 2.718$. In integer spaces, 3 is the most optimal base factor.
 * - If $n \pmod 3 == 0$, break strictly into 3s.
 * - If $n \pmod 3 == 1$, steal one 3 to combine into a 4 ($2 \cdot 2 > 1 \cdot 3$).
 * - If $n \pmod 3 == 2$, leave the residual 2 standalone ($2 \cdot 3 > 1 \cdot 3$).
 */
public class IntegerBreak {
    public static int integerBreak(int n) {
        if (n == 2) return 1; // 1 + 1 -> 1 * 1 = 1
        if (n == 3) return 2; // 2 + 1 -> 2 * 1 = 2

        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        // Multiply by the remaining chunk factor (which will be either 2, 3, or 4)
        return product * n;
    }

    public static void main(String[] args) {
        System.out.println("Max product break for 2: " + integerBreak(2));  // 1
        System.out.println("Max product break for 10: " + integerBreak(10)); // 36 (3 * 3 * 4)
    }
}