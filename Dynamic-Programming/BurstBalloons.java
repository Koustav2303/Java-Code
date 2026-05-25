public class BurstBalloons {
    public static int maxCoins(int[] nums) {
        // Pad the array with 1s on the boundaries
        int n = nums.length;
        int[] padded = new int[n + 2];
        padded[0] = 1;
        padded[n + 1] = 1;
        for (int i = 0; i < n; i++) padded[i + 1] = nums[i];
        
        int[][] dp = new int[n + 2][n + 2];
        
        // length of the subarray
        for (int len = 1; len <= n; len++) {
            for (int left = 1; left <= n - len + 1; left++) {
                int right = left + len - 1;
                
                // k is the index of the LAST balloon to burst in this window
                for (int k = left; k <= right; k++) {
                    dp[left][right] = Math.max(dp[left][right], 
                        padded[left - 1] * padded[k] * padded[right + 1] + 
                        dp[left][k - 1] + dp[k + 1][right]);
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int[] balloons = {3, 1, 5, 8};
        System.out.println("Max coins from bursting balloons: " + maxCoins(balloons));
    }
}