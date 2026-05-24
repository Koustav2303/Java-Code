public class PalindromicSubstringsDP {
    public static int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "aaa"; 
        System.out.println("Total palindromic substrings: " + countSubstrings(s));
    }
}