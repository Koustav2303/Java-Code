/**
 * PROBLEM: Ugly Number II
 * * Find the nth ugly number. Ugly numbers are positive integers whose prime factors are limited to 2, 3, and 5.
 * * Strategy: Prime Base Multiple Progression
 * Maintain a dynamic programming array `dp` where `dp[i]` stores the $(i+1)$-th ugly number. 
 * Initialize three tracking pointers (`p2`, `p3`, `p5`) to index into the `dp` table. 
 * At each step, calculate the next potential multiple for each prime: `dp[p2]*2`, `dp[p3]*3`, and `dp[p5]*5`. 
 * Select the minimum value to append to the table, then advance the pointers of any primes that produced 
 * that value to prevent duplicate states.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class UglyNumberII {
    public static int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1; // 1 is the baseline ugly number

        int p2 = 0, p3 = 0, p5 = 0;

        for (int i = 1; i < n; i++) {
            int next2 = dp[p2] * 2;
            int next3 = dp[p3] * 3;
            int next5 = dp[p5] * 5;

            int minNext = Math.min(next2, Math.min(next3, next5));
            dp[i] = minNext;

            // Increment pointers to advance the generation stream and avoid duplicates
            if (minNext == next2) p2++;
            if (minNext == next3) p3++;
            if (minNext == next5) p5++;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println("10th Ugly Number value: " + nthUglyNumber(10)); // 12 (Sequence: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12)
    }
}