/**
 * PROBLEM: Collatz Conjecture
 * * Given a positive integer n, simulate the Collatz (or $3n + 1$) sequence. Return the total number 
 * of operational steps required to drop the value down to 1.
 * * Strategy: Modular State Traversal
 * If the current value is even, divide it by 2. If it is odd, triple it and add 1 ($3n + 1$).
 * Run the loop using long primitives to prevent integer multiplication overflows.
 * * Complexity:
 * Time Complexity: Bounded empirically, highly optimized.
 * Space Complexity: O(1) constant working memory.
 */
public class CollatzConjecture {
    public static int countCollatzSteps(int n) {
        if (n <= 0) return -1;
        
        long val = n;
        int steps = 0;

        while (val != 1) {
            if ((val & 1) == 0) {
                val >>= 1; // Fast bitwise division by 2
            } else {
                val = 3 * val + 1;
            }
            steps++;
        }
        return steps;
    }

    public static void main(String[] args) {
        System.out.println("Steps for 6: " + countCollatzSteps(6));   // 8 (6 -> 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1)
        System.out.println("Steps for 19: " + countCollatzSteps(19)); // 20
    }
}