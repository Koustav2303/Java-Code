/**
 * PROBLEM: Deficient Numbers
 * * A deficient number is a number for which the sum of its proper divisors is strictly less than 
 * the number itself. Write an algorithm to check if a number is deficient.
 * * Strategy: Symmetric Factor Sieve
 * Scan possible factors from 2 up to sqrt(n). For each factor found, add both the factor and its 
 * corresponding partner (n / i) to a running divisor sum. Compare the final sum against the baseline number.
 */
public class DeficientNumbers {
    public static boolean isDeficient(int n) {
        if (n <= 1) return true; // 1 has no proper divisors less than itself, proper sum = 0 < 1

        int divisorSum = 1; // 1 is always a proper divisor

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                divisorSum += i;
                if (i * i != n) {
                    divisorSum += n / i; // Add the symmetric counterpart
                }
            }
        }
        return divisorSum < n;
    }

    public static void main(String[] args) {
        System.out.println("Is 15 deficient? " + isDeficient(15)); // true (1 + 3 + 5 = 9 < 15)
        System.out.println("Is 12 deficient? " + isDeficient(12)); // false (1 + 2 + 3 + 4 + 6 = 16 > 12)
    }
}