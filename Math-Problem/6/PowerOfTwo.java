/**
 * PROBLEM: Power of Two
 * * Given an integer n, return true if it is a power of two. Otherwise, return false.
 * * Strategy: Underflow Bitmask Sieve
 * A positive integer is a power of 2 if and only if its binary representation contains exactly one active bit. 
 * We can clear the lowest set bit of a number using the bitwise operation `n & (n - 1)`. 
 * If the result is exactly 0, the number had only one active bit, confirming it is a power of two.
 * * Complexity:
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
public class PowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        // Clear the lowest active bit and verify if the result drops to zero
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println("Is 16 a power of 2? " + isPowerOfTwo(16)); // true
        System.out.println("Is 14 a power of 2? " + isPowerOfTwo(14)); // false
    }
}