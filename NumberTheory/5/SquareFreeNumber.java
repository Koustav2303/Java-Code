/**
 * PROBLEM: Square-Free Number
 * * An integer is square-free if it is not divisible by any perfect square greater than 1. 
 * Write an algorithm to verify if a number satisfies this condition.
 * * Strategy: Trial Division Exponent Check
 * Extract prime factors using trial division. If any prime factor divides the number 
 * more than once, it contains a perfect square factor ($p^2$), meaning it cannot be square-free.
 * * Complexity:
 * Time Complexity: O(sqrt(N))
 * Space Complexity: O(1)
 */
public class SquareFreeNumber {
    public static boolean isSquareFree(int n) {
        if (n <= 0) return false;
        
        // Check divisibility by 2 and its square
        if (n % 2 == 0) {
            n /= 2;
            if (n % 2 == 0) return false;
        }

        // Check odd integers up to the square root
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                n /= i;
                if (n % i == 0) return false; // Divisible by i^2
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is 10 square-free? " + isSquareFree(10)); // true (10 = 2 * 5)
        System.out.println("Is 12 square-free? " + isSquareFree(12)); // false (divisible by 2^2 = 4)
    }
}