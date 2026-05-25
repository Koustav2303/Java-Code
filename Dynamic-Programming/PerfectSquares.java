import java.util.Arrays;

public class PerfectSquares {
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            // Check all perfect squares less than or equal to i
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - (j * j)] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 12; // 4 + 4 + 4
        System.out.println("Minimum perfect squares to make " + n + ": " + numSquares(n));
    }
}