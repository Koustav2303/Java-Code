/**
 * PROBLEM: Catalan Number
 * * Compute the n-th Catalan number. Catalan numbers are a sequence of natural numbers that occur 
 * in various counting problems, such as calculating the number of valid parenthesizations or 
 * unique binary search trees with n keys.
 * * Strategy: Dynamic Programming Recurrence
 * Apply the Catalan recurrence relation:
 * $$C_n = \sum_{i=0}^{n-1} C_i \cdot C_{n-1-i}$$
 * Initialize $C_0 = 1$ and $C_1 = 1$, then build up the values iteratively to avoid recursive overlap.
 * * Complexity:
 * Time Complexity: O(N^2)
 * Space Complexity: O(N)
 */
public class CatalanNumber {
    public static long getCatalanNumber(int n) {
        if (n <= 1) return 1;

        long[] catalan = new long[n + 1];
        catalan[0] = 1;
        catalan[1] = 1;

        for (int i = 2; i <= n; i++) {
            catalan[i] = 0;
            for (int j = 0; j < i; j++) {
                catalan[i] += catalan[j] * catalan[i - 1 - j];
            }
        }
        return catalan[n];
    }

    public static void main(String[] args) {
        System.out.println("4th Catalan Number: " + getCatalanNumber(4)); // 14
        System.out.println("5th Catalan Number: " + getCatalanNumber(5)); // 42
    }
}