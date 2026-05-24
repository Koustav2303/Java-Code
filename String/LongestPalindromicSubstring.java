public class LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes (e.g., "aba")
            int len1 = expandAroundCenter(s, i, i);
            // Even length palindromes (e.g., "abba")
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int len = Math.max(len1, len2);
            
            // If we found a longer palindrome, update start and end indices
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the length of the palindrome found
        return right - left - 1;
    }

    public static void main(String[] args) {
        String text = "babad";
        System.out.println("String: " + text);
        System.out.println("Longest Palindromic Substring: " + longestPalindrome(text));
    }
}