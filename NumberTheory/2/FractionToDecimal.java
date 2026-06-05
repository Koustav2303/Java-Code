import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Fraction to Recurring Decimal
 * * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part repeats, enclose the repeating part in parentheses.
 * * Strategy: Remainder Hashing State Maps
 * Simulate fractional long division manually. To detect recurring fractional cycles, maintain a HashMap 
 * that tracks the exact `remainder` as the key and its corresponding string character position index as the value. 
 * If a remainder repeats, insert parentheses around the substring starting from that indexed position.
 */
public class FractionToDecimal {
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder sb = new StringBuilder();
        // Xor sign bit flags to catch conflicting polarity states
        if ((numerator < 0) ^ (denominator < 0)) sb.append("-");

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Process integer component boundary
        sb.append(num / den);
        long remainder = num % den;
        if (remainder == 0) return sb.toString();

        sb.append(".");
        Map<Long, Integer> remainderMap = new HashMap<>();

        // Process fractional component boundary via cyclic maps
        while (remainder != 0) {
            if (remainderMap.containsKey(remainder)) {
                int openParenthesisIndex = remainderMap.get(remainder);
                sb.insert(openParenthesisIndex, "(");
                sb.append(")");
                break;
            }

            remainderMap.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / den);
            remainder %= den;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("1 / 2 = " + fractionToDecimal(1, 2));   // 0.5
        System.out.println("2 / 3 = " + fractionToDecimal(2, 3));   // 0.(6)
        System.out.println("4 / 333 = " + fractionToDecimal(4, 333)); // 0.(012)
    }
}