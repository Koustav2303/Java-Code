/**
 * PROBLEM: Fibonacci Matrix Exponentiation
 * * Compute the N-th Fibonacci number modulo 10^9 + 7 for an extremely large N (up to 10^18).
 * * Strategy: Linear Recurrence Transformation
 * Standard iteration takes $O(N)$ time, which overflows the time limit for huge inputs. Instead, 
 * rewrite the linear recurrence as a matrix transformation equation:
 * $$\begin{pmatrix} F_{n+1} & F_n \\ F_n & F_{n-1} \end{pmatrix} = \begin{pmatrix} 1 & 1 \\ 1 & 0 \end{pmatrix}^n$$
 * Compute the $n$-th power of the transformation matrix using binary exponentiation in $O(\log N)$ time.
 */
public class FibonacciMatrixExponentiation {
    private static final long MOD = 1_000_000_007;

    public static int fib(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long[][] T = {{1, 1}, {1, 0}};
        matrixPower(T, n - 1);
        return (int) T[0][0];
    }

    private static void matrixPower(long[][] matrix, long exp) {
        long[][] result = {{1, 0}, {0, 1}}; // Identity Matrix setup
        long[][] base = matrix;

        while (exp > 0) {
            if ((exp & 1) == 1) result = multiply(result, base);
            base = multiply(base, base);
            exp >>= 1;
        }

        matrix[0][0] = result[0][0]; matrix[0][1] = result[0][1];
        matrix[1][0] = result[1][0]; matrix[1][1] = result[1][1];
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
        long n = 10;
        System.out.println("10th Fibonacci number: " + fib(n)); // 55
        System.out.println("1000000000th Fibonacci mod: " + fib(1000000000L)); // 21
    }
}