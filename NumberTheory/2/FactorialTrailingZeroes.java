/**
 * PROBLEM: Factorial Trailing Zeroes
 * * Given an integer n, return the number of trailing zeroes in $n!$.
 * * Strategy: Legendre's Prime Factorization Formula
 * Trailing zeros are produced by prime pairs of 2 and 5. In any factorial sequence, multiples of 2 
 * outnumber multiples of 5 significantly. Thus, counting trailing zeros simplifies to counting 
 * the total powers of 5 contained within the sequence. 
 * Apply Legendre's formula: $\sum \lfloor n / 5^i \rfloor$.
 * * Complexity:
 * Time Complexity: $O(\log_5(N))$
 * Space Complexity: $O(1)$
 */
public class FactorialTrailingZeroes {
    public static int trailingZeroes(int n) {
        int count = 0;
        // Continuously divide n by powers of 5 to extract inner factor counts
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Trailing zeroes in 5!: " + trailingZeroes(5));   // 1 (5! = 120)
        System.out.println("Trailing zeroes in 30!: " + trailingZeroes(30)); // 7
    }
}