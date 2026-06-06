public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        // Negative numbers and numbers ending in 0 (except 0 itself) are never palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        // When x <= reversedHalf, we've reached the middle of the number
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by reversedHalf / 10
        return x == reversedHalf || x == reversedHalf / 10;
    }

    public static void main(String[] args) {
        int num = 1221;
        System.out.println("Is " + num + " a palindrome? " + isPalindrome(num));
        
        int num2 = 123;
        System.out.println("Is " + num2 + " a palindrome? " + isPalindrome(num2));
    }
}