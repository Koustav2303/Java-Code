/**
 * PROBLEM: Extended Euclidean Algorithm
 * * Given two integers a and b, find coefficients x and y such that: ax + by = gcd(a, b).
 * * Strategy: Bottom-Up Coefficient Tracking
 * Extend the classic Euclidean algorithm by tracking the linear combinations of a and b 
 * through recursive unwinding stack frames. Update values using the transformation: 
 * next_x = prior_y - (a / b) * prior_x.
 * * Complexity:
 * Time Complexity: O(log(min(a, b)))
 * Space Complexity: O(log(min(a, b))) for recursion stack call frames.
 */
public class ExtendedGCD {
    static class Result {
        int gcd, x, y;
        Result(int g, int x, int y) { this.gcd = g; this.x = x; this.y = y; }
    }

    public static Result extendedGCD(int a, int b) {
        if (b == 0) {
            return new Result(a, 1, 0); // Base case identity setup
        }

        Result prev = extendedGCD(b, a % b);

        // Update coefficients based on recursive results
        int currentX = prev.y;
        int currentY = prev.x - (a / b) * prev.y;

        return new Result(prev.gcd, currentX, currentY);
    }

    public static void main(String[] args) {
        int a = 30, b = 20;
        Result res = extendedGCD(a, b);
        System.out.println("GCD: " + res.gcd + ", Coefficients: x = " + res.x + ", y = " + res.y);
        // 30*(1) + 20*(-1) = 10 (GCD)
    }
}