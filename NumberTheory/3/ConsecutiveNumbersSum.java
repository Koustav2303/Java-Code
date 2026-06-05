/**
 * PROBLEM: Consecutive Numbers Sum
 * * Given an integer n, return the number of ways to write n as the sum of consecutive positive integers.
 * * Strategy: Arithmetic Sequence Bound Reduction
 * Expressing a number as a sum of $k$ consecutive integers starting from $x+1$ follows the formula:
 * $$n = (x + 1) + (x + 2) + \dots + (x + k) = k \cdot x + \frac{k(k + 1)}{2}$$
 * Rearranging the terms gives:
 * $$n - \frac{k(k + 1)}{2} = k \cdot x$$
 * Since $x \ge 0$, we iterate through possible values of $k$ starting from 1 as long as $\frac{k(k + 1)}{2} \le n$. 
 * If $(n - \frac{k(k + 1)}{2}) \pmod k == 0$, a valid consecutive integer sequence is found.
 */
public class ConsecutiveNumbersSum {
    public static int consecutiveNumbersSum(int n) {
        int ways = 0;
        int k = 1;
        
        // Loop while the minimum sum configuration for k elements is <= n
        while (n - (k * (k + 1)) / 2 >= 0) {
            int remainder = n - (k * (k + 1)) / 2;
            if (remainder % k == 0) {
                ways++;
            }
            k++;
        }
        return ways;
    }

    public static void main(String[] args) {
        System.out.println("Ways to express 5: " + consecutiveNumbersSum(5));   // 2 (5, 2+3)
        System.out.println("Ways to express 15: " + consecutiveNumbersSum(15)); // 4 (15, 7+8, 4+5+6, 1+2+3+4+5)
    }
}