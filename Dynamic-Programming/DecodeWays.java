public class DecodeWays {
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        
        int dp1 = 1, dp2 = 1; // dp1 = dp[i-1], dp2 = dp[i-2]
        
        for (int i = 1; i < s.length(); i++) {
            int current = 0;
            if (s.charAt(i) != '0') current += dp1;
            
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) current += dp2;
            
            dp2 = dp1;
            dp1 = current;
        }
        return dp1;
    }

    public static void main(String[] args) {
        String code = "226"; // BBF, BZ, VF
        System.out.println("Decode ways for " + code + ": " + numDecodings(code));
    }
}