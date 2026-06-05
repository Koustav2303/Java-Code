/**
 * PROBLEM: Armstrong Numbers
 * * An Armstrong number (or Narcissistic number) is a number that is the sum of its own digits 
 * each raised to the power of the number of digits. Check if a number fits this property.
 * * Strategy: Radix Count and Digit Unrolling
 * Count the digits first to determine the power exponent $k$. Then, strip the digits sequentially 
 * using modular reduction, raising each digit to the power of $k$ and updating a running sum.
 * * Complexity:
 * Time Complexity: $O(\log_{10}(N))$
 * Space Complexity: $O(1)$
 */
public class ArmstrongNumbers {
    public static boolean isArmstrong(int n) {
        if (n < 0) return false;

        int numDigits = String.valueOf(n).length();
        int temp = n;
        long sum = 0;

        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, numDigits);
            temp /= 10;
        }

        return sum == n;
    }

    public static void main(String[] args) {
        System.out.println("Is 153 an Armstrong number? " + isArmstrong(153)); // true (1^3 + 5^3 + 3^3 = 153)
        System.out.println("Is 123 an Armstrong number? " + isArmstrong(123)); // false
    }
}