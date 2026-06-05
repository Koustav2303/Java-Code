import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Prime Factorization
 * * Compute and return the full prime factorization decomposition of a given positive integer n.
 * * Strategy: Optimized Fundamental Theorem of Arithmetic Trial Division
 * Extract all factors of 2 first. Then loop through odd integers starting from 3 up to $\sqrt{n}$. 
 * If a dividing factor is found, continuously divide the number by that factor to eliminate all of its 
 * occurrences before moving to the next odd integer. Any remainder left at the end $> 2$ must be prime.
 * * Complexity:
 * Time Complexity: $O(\sqrt{N})$ worst-case scenario (e.g., when number is prime).
 */
public class PrimeFactorization {
    public static List<Integer> getPrimeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        if (n < 2) return factors;

        // Extract all factors of 2
        while (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }

        // Extract odd prime factors up to the square root
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        // If n is still greater than 2, the remaining value must be a prime factor itself
        if (n > 2) {
            factors.add(n);
        }

        return factors;
    }

    public static void main(String[] args) {
        int target = 315;
        System.out.println("Prime factors of " + target + ": " + getPrimeFactors(target)); // [3, 3, 5, 7]
    }
}