/**
 * PROBLEM: Euler's Totient Function
 * * Given a positive integer n, compute phi(n), which counts the total number of integers 
 * up to n that are relatively prime (coprime) to n.
 * * Strategy: Euler's Product Formula
 * The product formula states that phi(n) = n * Product(1 - 1/p) for all distinct prime factors p of n. 
 * Loop through possible factors up to sqrt(n). If a prime factor is located, divide out all of its 
 * occurrences and update our running total.
 * * Complexity:
 * Time Complexity: O(sqrt(N))
 * Space Complexity: O(1)
 */
public class EulersTotient {
    public static int phi(int n) {
        int result = n;

        // Check distinct prime factors up to the square root of n
        for (int p = 2; p * p <= n; p++) {
            if (n % p == 0) {
                // p is a prime factor; eliminate all its duplicates
                while (n % p == 0) {
                    n /= p;
                }
                // Apply the product transformation step: result = result * (1 - 1/p)
                result -= result / p;
            }
        }

        // If n is still greater than 1, the remaining value must be a prime factor itself
        if (n > 1) {
            result -= result / n;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Phi of " + n + " is: " + phi(n)); // 4 (Numbers are 1, 3, 7, 9)
    }
}