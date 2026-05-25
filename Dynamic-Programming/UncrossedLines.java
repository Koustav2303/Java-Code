import java.util.Arrays;

public class UncrossedLines {
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] dp = new int[n + 1]; // Optimized to 1D array
        
        for (int i = 1; i <= m; i++) {
            int prev = 0; // Represents dp[i-1][j-1]
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = temp;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 4, 2};
        int[] nums2 = {1, 2, 4};
        System.out.println("Maximum uncrossed lines: " + maxUncrossedLines(nums1, nums2));
    }
}