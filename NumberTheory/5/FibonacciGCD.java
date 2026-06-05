/**
 * PROBLEM: Fibonacci GCD
 * * Compute the Greatest Common Divisor of two massive Fibonacci numbers $F_m$ and $F_n$. 
 * Since the values grow exponentially, return the final value modulo 10^9 + 7.
 * * Strategy: Number Theory Recurrence Reduction
 * Fibonacci sequences possess a beautiful number theory property: the GCD of two Fibonacci numbers 
 * is equal to the Fibonacci number of their GCD:
 * $$\text{gcd}(F_m, F_n) = F_{\text{gcd}(m, n)}$$
 * First, calculate the GCD of the two indices $m$ and $n$ using the Euclidean algorithm. 
 * Then, compute that specific Fibonacci index using fast matrix exponentiation.
 */
public class FibonacciGCD {
    private static final long MOD = 1_000_000_007;

    private static int gcd(int a, int b) {
        while (b != 0) { int t = b; b = a % b; a = t; }
        return a;
    }

    public static int getFibonacciGCD(int m, int n) {
        int targetIndex = gcd(m, n);
        return calculateFibonacci(targetIndex);
    }

    private static int calculateFibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long[][] T = {{1, 1}, {1, 0}};
        matrixPower(T, n - 1);
        return (int) T[0][0];
    }

    private static void matrixPower(long[][] T, int exp) {
        long[][] res = {{1, 0}, {0, 1}};
        long[][] base = T;
        while (exp > 0) {
            if ((exp & 1) == 1) res = multiply(res, base);
            base = multiply(base, base);
            exp >>= 1;
        }
        T[0][0] = res[0][0]; T[0][1] = res[0][1];
        T[1][0] = res[1][0]; T[1][1] = res[1][1];
    }

    private static long[][] multiply(long[][] A, long[][] B) {
        long[][] C = new long[2][2];
        C[0][0] = (A[0][0] * B[0][0] + A[0][1] * B[1][0]) % MOD;
        C[0][1] = (A[0][0] * B[0][1] + A[0][1] * B[1][1]) % MOD;
        C[1][0] = (A[1][0] * B[0][0] + A[1][1] * B[1][0]) % MOD;
        C[1][1] = (A[1][0] * B[0][1] + A[1][1] * B[1][1]) % MOD;
        return C;
    }

    public static void main(String[] args) {
        System.out.println("GCD of F(10) and F(15): " + getFibonacciGCD(10, 15)); // F(gcd(10,15)) = F(5) = 5
    }
}