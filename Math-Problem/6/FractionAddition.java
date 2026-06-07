import java.util.Scanner;

/**
 * PROBLEM: Fraction Addition and Subtraction
 * * Given a string expression representing an equation of fraction additions and subtractions, 
 * return the calculation result in string format. The final fraction must be an irreducible fraction.
 * * Strategy: Global Common Denominator Sieve
 * Initialize your running numerator to 0 and your common denominator to 1. Parse individual fraction 
 * tokens from the string expression using a regular expression splitter. 
 * Calculate new fractional updates using the cross-multiplication formula:
 * $$ \frac{A}{B} \pm \frac{C}{D} = \frac{A \cdot D \pm C \cdot B}{B \cdot D} $$
 * Reduce the final fraction by dividing both terms by their Greatest Common Divisor (GCD).
 */
public class FractionAddition {
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    public static String fractionAddition(String expression) {
        // Use regex scanning to tokenize numeric sign pairings cleanly
        Scanner sc = new Scanner(expression).useDelimiter("(?=[-+])|/");
        int num = 0, den = 1;

        while (sc.hasNext()) {
            int nextNum = sc.nextInt();
            int nextDen = sc.nextInt();
            
            // Apply cross-multiplication balance formulas
            num = num * nextDen + nextNum * den;
            den = den * nextDen;

            int commonGcd = gcd(num, den);
            num /= commonGcd;
            den /= commonGcd;
        }
        sc.close();
        return num + "/" + den;
    }

    public static void main(String[] args) {
        System.out.println("Result of '-1/2+1/2': " + fractionAddition("-1/2+1/2"));     // 0/1
        System.out.println("Result of '1/3-1/2': " + fractionAddition("1/3-1/2"));       // -1/6
    }
}