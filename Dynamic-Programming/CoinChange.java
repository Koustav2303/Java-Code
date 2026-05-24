import java.util.Arrays;

public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Fill with impossible max value
        dp[0] = 0;
        
        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (a - coin >= 0) {
                    dp[a] = Math.min(dp[a], 1 + dp[a - coin]);
                }
            }
        }
        return dp[amount] != amount + 1 ? dp[amount] : -1;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Minimum coins for " + amount + ": " + coinChange(coins, amount));
    }
}