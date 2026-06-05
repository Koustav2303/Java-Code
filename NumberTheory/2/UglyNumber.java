/**
 * PROBLEM: Ugly Number
 * * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * Given an integer n, return true if n is an ugly number.
 * * Strategy: Consecutive Base Division Pruning
 * If $n \le 0$, it cannot be an ugly number by definition. Divide out all factors of 2, 3, and 5 
 * continuously using loops. If the number reduces to exactly 1 at the end of this pruning process, 
 * it is an ugly number.
 * * Complexity:
 * Time Complexity: $O(\log(N))$
 * Space Complexity: O(1)
 */
public class UglyNumber {
    public static boolean isUgly(int n) {
        if (n <= 0) return false;

        // Continuously divide out the valid base prime metrics
        int[] bases = {2, 3, 5};
        for (int base : bases) {
            while (n % base == 0) {
                n /= base;
            }
        }
        
        return n == 1;
    }

    public static void main(String[] args) {
        System.out.println("Is 6 ugly? " + isUgly(6));   // true (2 * 3)
        System.out.println("Is 14 ugly? " + isUgly(14)); // false (contains prime factor 7)
    }
}