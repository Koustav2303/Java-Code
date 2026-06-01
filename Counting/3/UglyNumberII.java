/**
 * PROBLEM: Ugly Number II
 * * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * Given an integer n, return the nth ugly number.
 * * Approach:
 * Dynamic Programming. Keep 3 pointers (p2, p3, p5) tracking which index in our DP array 
 * should be multiplied by 2, 3, or 5 to generate the next ugly number. 
 * Take the minimum of these three products to append to the array.
 */
public class UglyNumberII {
    public static int nthUglyNumber(int n) {
        if (n <= 0) return 0;
        
        int[] dp = new int[n];
        dp[0] = 1; // 1 is the first ugly number
        
        int p2 = 0, p3 = 0, p5 = 0;
        
        for (int i = 1; i < n; i++) {
            int next2 = dp[p2] * 2;
            int next3 = dp[p3] * 3;
            int next5 = dp[p5] * 5;
            
            int nextUgly = Math.min(next2, Math.min(next3, next5));
            dp[i] = nextUgly;
            
            // Increment the pointers if they produced the chosen ugly number
            if (nextUgly == next2) p2++;
            if (nextUgly == next3) p3++;
            if (nextUgly == next5) p5++;
        }
        
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println("10th ugly number: " + nthUglyNumber(10)); // 12
    }
}