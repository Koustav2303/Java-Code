/**
 * PROBLEM: Chinese Remainder Theorem (CRT)
 * * Solve a system of simultaneous modular congruences: x % num[i] == rem[i], 
 * where all elements inside num are pairwise coprime integers.
 * * Strategy: Gauss-CRT Optimization Sequence
 * Calculate the global product of all moduli: Prod = num[0] * num[1] * ... * num[k]. 
 * For each equation, find the partial product PP[i] = Prod / num[i]. 
 * Compute the modular inverse of PP[i] modulo num[i], denoted as Inv[i]. 
 * The system solution is given by: Sum(rem[i] * PP[i] * Inv[i]) % Prod.
 * * Complexity:
 * Time Complexity: O(K log(N)) where K is array size.
 * Space Complexity: O(1)
 */
public class ChineseRemainderTheorem {
    private static int modInverse(int a, int m) {
        int m0 = m, y = 0, x = 1;
        if (m == 1) return 0;
        while (a > 1) {
            int q = a / m;
            int t = m;
            m = a % m; a = t; t = y;
            y = x - q * y; x = t;
        }
        if (x < 0) x += m0;
        return x;
    }

    public static int findMinX(int[] num, int[] rem) {
        int prod = 1;
        for (int n : num) prod *= n;

        int result = 0;
        for (int i = 0; i < num.length; i++) {
            int partialProd = prod / num[i];
            int inverse = modInverse(partialProd, num[i]);
            result += rem[i] * partialProd * inverse;
        }
        return result % prod;
    }

    public static void main(String[] args) {
        int[] num = {3, 5, 7};
        int[] rem = {2, 3, 2};
        System.out.println("Value of x is: " + findMinX(num, rem)); // 23 -> (23%3=2, 23%5=3, 23%7=2)
    }
}