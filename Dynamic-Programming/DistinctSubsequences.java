public class DistinctSubsequences {
    public static int numDistinct(String s, String t) {
        int m = t.length(), n = s.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // An empty string 't' can be formed 1 way from any string 's' (by deleting all chars)
        for (int j = 0; j <= n; j++) dp[0][j] = 1;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    // We can either USE the matching character, or IGNORE it and look earlier
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    // We must ignore the current character of 's'
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "rabbbit", t = "rabbit";
        System.out.println("Distinct ways to form '" + t + "' from '" + s + "': " + numDistinct(s, t));
    }
}