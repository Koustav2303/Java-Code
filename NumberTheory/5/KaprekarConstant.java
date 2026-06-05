import java.util.Arrays;

/**
 * PROBLEM: Kaprekar Constant
 * * Take any four-digit number (with at least two distinct digits). Arrange the digits in descending 
 * and ascending order to form two new numbers, then subtract the smaller number from the larger number. 
 * Simulate this routine and return the steps needed to hit the invariant Kaprekar Constant (6174).
 * * Strategy: Positional Digit Parsing
 * Convert the number into a 4-element array. Sort the array to form the smallest value, 
 * and reverse it to form the largest value. Subtract them and track the step execution count.
 */
public class KaprekarConstant {
    public static int countKaprekarSteps(int num) {
        int steps = 0;
        while (num != 6174) {
            if (num == 0) return -1; // Protect against invalid inputs
            
            int[] digits = new int[4];
            for (int i = 0; i < 4; i++) {
                digits[i] = num % 10;
                num /= 10;
            }

            Arrays.sort(digits);
            int asc = 0, desc = 0;
            for (int i = 0; i < 4; i++) {
                asc = asc * 10 + digits[i];
                desc = desc * 10 + digits[3 - i];
            }

            num = desc - asc;
            steps++;
            if (num == 0) return -1; // Edge case: all digits identical (e.g., 1111) cannot proceed
        }
        return steps;
    }

    public static void main(String[] args) {
        System.out.println("Steps for 3524: " + countKaprekarSteps(3524)); // 3 steps to reach 6174
    }
}