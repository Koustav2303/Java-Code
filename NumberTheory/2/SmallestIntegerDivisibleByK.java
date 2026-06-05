/**
 * PROBLEM: Smallest Integer Divisible by K
 * * Given a positive integer k, find the length of the smallest positive integer n such that 
 * n is divisible by k, and n only contains the digit 1. If no such n exists, return -1.
 * * Strategy: Remainder State Modulo Transitions
 * Instead of calculating massive integers containing only 1s (which overflows quickly), 
 * generate remainders sequentially using modular arithmetic: $\text{remainder} = (\text{remainder} \cdot 10 + 1) \pmod k$.
 * By the Pigeonhole Principle, if we process the loop $k$ times and never hit a remainder of 0, 
 * a remainder state must have repeated. This indicates an infinite loop cycle, meaning no solution exists.
 */
public class SmallestIntegerDivisibleByK {
    public static int smallestRepunitDivByK(int k) {
        // Optimization check: numbers ending in 2, 4, 5, 6, 8, or 0 can never divide a number ending in 1
        if (k % 2 == 0 || k % 5 == 0) return -1;

        int remainder = 0;
        for (int length = 1; length <= k; length++) {
            remainder = (remainder * 10 + 1) % k;
            if (remainder == 0) {
                return length; // Found exact dividing width scale match
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Length for k=3: " + smallestRepunitDivByK(3)); // 3 (111 % 3 == 0)
        System.out.println("Length for k=2: " + smallestRepunitDivByK(2)); // -1
    }
}