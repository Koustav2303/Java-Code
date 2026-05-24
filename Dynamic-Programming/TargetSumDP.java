import java.util.Arrays;

public class TargetSumDP {
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) return 0;
        
        int subsetSum = (sum + target) / 2;
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1;
        
        for (int num : nums) {
            for (int i = subsetSum; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[subsetSum];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println("Ways to hit target " + target + ": " + findTargetSumWays(nums, target));
    }
}