/**
 * PROBLEM: Valid Perfect Square
 * * Given a positive integer num, return true if num is a perfect square, or false otherwise.
 * Do not use any built-in library functions like Math.sqrt().
 * * Strategy: Newton-Raphson Approximations
 * Finding the square root of $N$ is equivalent to solving the roots of the function $f(x) = x^2 - N = 0$. 
 * Apply the Newton-Raphson tangent formula:
 * $$x_{n+1} = x_n - \frac{f(x_n)}{f'(x_n)} = x_n - \frac{x_n^2 - N}{2x_n} = \frac{1}{2} \left( x_n + \frac{N}{x_n} \right)$$
 * Start with a guess $x_0 = N$. Iteratively update the value until the integer component converges.
 */
public class ValidPerfectSquare {
    public static boolean isPerfectSquare(int num) {
        if (num < 1) return false;
        
        long x = num; // Use long to safeguard against internal intermediate product overflows
        
        // Newton-Raphson integer division convergence loop
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        
        return x * x == num;
    }

    public static void main(String[] args) {
        System.out.println("Is 16 a perfect square? " + isPerfectSquare(16)); // true
        System.out.println("Is 14 a perfect square? " + isPerfectSquare(14)); // false
    }
}