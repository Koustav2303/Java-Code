public class PalindromeChecker {
    public static void main(String[] args) {
        String input = "A man a plan a canal Panama";
        System.out.println("Is Palindrome: " + isPalindrome(input));
    }

    public static boolean isPalindrome(String s) {
        String clean = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int i = 0, j = clean.length() - 1;
        while (i < j) {
            if (clean.charAt(i++) != clean.charAt(j--)) return false;
        }
        return true;
    }
}