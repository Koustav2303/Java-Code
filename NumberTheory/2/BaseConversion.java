/**
 * PROBLEM: Base Conversion
 * * Given an integer num and a target base b (where 2 <= b <= 16), return its string representation 
 * in that base system. Handle negative integers gracefully.
 * * Strategy: Radix Remainder Extraction
 * Repeatedly take the modulo of the number by the target base to extract the least significant digit, 
 * then integer-divide the number by the base to shift right. Map remainders greater than 9 
 * to alphabetical characters (A-F).
 * * Complexity:
 * Time Complexity: $O(\log_b(N))$
 * Space Complexity: $O(\log_b(N))$ buffer string depth.
 */
public class BaseConversion {
    public static String convertToBase(int num, int base) {
        if (num == 0) return "0";
        if (base < 2 || base > 16) throw new IllegalArgumentException("Base must be between 2 and 16");

        StringBuilder sb = new StringBuilder();
        boolean isNegative = num < 0;
        long n = Math.abs((long) num); // Use long to prevent Integer.MIN_VALUE overflow anomalies

        char[] symbols = "0123456789ABCDEF".toCharArray();

        while (n > 0) {
            int remainder = (int) (n % base);
            sb.append(symbols[remainder]);
            n /= base;
        }

        if (isNegative) sb.append("-");
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println("100 in Base 2: " + convertToBase(100, 2));   // 1100100
        System.out.println("255 in Base 16: " + convertToBase(255, 16)); // FF
        System.out.println("-45 in Base 7: " + convertToBase(-45, 7));   // -63
    }
}