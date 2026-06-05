/**
 * PROBLEM: Smith Number
 * * A Smith number is a composite number the sum of whose digits is equal to the sum of the digits 
 * of its prime factors (including multiple occurrences of the same factor).
 * * Strategy: Factor Sum Sifting
 * First, check if the number is composite. Calculate its digit sum. 
 * Extract all prime factors using trial division up to $\sqrt{n}$, and add the digit sums 
 * of each factor to a running total. Check if the two sums match.
 */
public class SmithNumber {
    private static int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static boolean isSmithNumber(int n) {
        int original = n;
        int sumOfFactorsDigits = 0;
        int temp = n;

        // Step 1: Extract factor components of 2
        while (temp % 2 == 0) {
            sumOfFactorsDigits += 2;
            temp /= 2;
        }

        // Step 2: Extract remaining odd prime factors
        for (int i = 3; i * i <= temp; i += 2) {
            while (temp % i == 0) {
                sumOfFactorsDigits += getDigitSum(i);
                temp /= i;
            }
        }
        if (temp > 1 && temp != original) {
            sumOfFactorsDigits += getDigitSum(temp);
        }

        // A Smith number must be composite (cannot equal original baseline)
        if (temp == original) return false;

        return getDigitSum(original) == sumOfFactorsDigits;
    }

    public static void main(String[] args) {
        System.out.println("Is 4937775 a Smith number? " + isSmithNumber(4937775)); // true
        System.out.println("Is 13 a Smith number? " + isSmithNumber(13));           // false (Prime)
    }
}