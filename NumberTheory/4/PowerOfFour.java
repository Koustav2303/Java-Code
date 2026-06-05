/**
 * PROBLEM: Power of Four
 * * Given an integer n, return true if it is a power of four. Otherwise, return false.
 * * Strategy: Bitmask Property Inspection
 * A number is a power of 4 if it satisfies two conditions:
 * 1. It is a power of 2: `(n > 0) && ((n & (n - 1)) == 0)`.
 * 2. Its single active bit resides on an even index position. We verify this using the specific 
 * hexadecimal bitmask `0x55555555` (binary `01010101...`).
 */
public class PowerOfFour {
    public static boolean isPowerOfFour(int n) {
        // If n is a power of two and its bit is on a valid position mask, it is a power of four
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }

    public static void main(String[] args) {
        System.out.println("Is 16 a power of 4? " + isPowerOfFour(16)); // true
        System.out.println("Is 8 a power of 4? " + isPowerOfFour(8));   // false
    }
}