/**
 * PROBLEM: Power of Three
 * * Given an integer n, return true if it is a power of three. Otherwise, return false. 
 * Can you solve it without using loops or recursion?
 * * Strategy: Max Power Bounds Verification
 * Since the input parameter is bounded by a 32-bit signed integer, the maximum possible power of 3 
 * that fits within this range is $3^{19} = 1,162,261,467$. Because 3 is a prime number, any integer $N$ 
 * is a power of 3 if and only if it is positive and divides this maximum value perfectly.
 * * Complexity:
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
public class PowerOfThree {
    public static boolean isPowerOfThree(int n) {
        // 1162261467 is exactly 3^19
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        System.out.println("Is 27 a power of 3? " + isPowerOfThree(27)); // true
        System.out.println("Is 45 a power of 3? " + isPowerOfThree(45)); // false
    }
}