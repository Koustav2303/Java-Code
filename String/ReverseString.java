import java.util.Arrays;

public class ReverseString {
    public static String reverse(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // Swap the characters
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            
            left++;
            right--;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String original = "hello world";
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reverse(original));
    }
}