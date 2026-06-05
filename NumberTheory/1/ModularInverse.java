/**
 * PROBLEM: Modular Multiplicative Inverse
 * * Find an integer x such that (a * x) % m == 1. The inverse exists if and only if a and m are coprime.
 * * Strategy: Extended GCD Mapping
 * The equation (a * x) % m = 1 can be rewritten as ax - 1 = my, which is equivalent to ax + m(-y) = 1. 
 * Since gcd(a, m) must be 1, we can compute this using the Extended Euclidean Algorithm. 
 * If the resulting 'x' is negative, add 'm' to bring it into the correct positive modular range.
 */
public class ModularInverse {
    static class Result {
        int gcd, x, y;
        Result(int g, int x, int y) { this.gcd = g; this.x = x; this.y = y; }
    }

    private static Result extendedGCD(int a, int b) {
        if (b == 0) return new Result(a, 1, 0);
        Result prev = extendedGCD(b, a % b);
        return new Result(prev.gcd, prev.y, prev.x - (a / b) * prev.y);
    }

    public static int modInverse(int a, int m) {
        Result res = extendedGCD(a, m);
        if (res.gcd != 1) {
            System.out.println("Inverse does not exist (not coprime).");
            return -1;
        }
        // Bring negative coefficients into positive bounds
        return (res.x % m + m) % m;
    }

    public static void main(String[] args) {
        int a = 3, m = 11;
        System.out.println("Modular Inverse is: " + modInverse(a, m)); // 4 -> (3 * 4) % 11 = 12 % 11 = 1
    }
}