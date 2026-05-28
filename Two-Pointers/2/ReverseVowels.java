/**
 * PROBLEM: Reverse Vowels of a String
 * * Given a string s, reverse only all the vowels in the string and return it.
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases.
 * * Example:
 * Input: s = "hello"
 * Output: "holle"
 */
public class ReverseVowels {
    public static String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        String vowels = "aeiouAEIOU";
        
        while (left < right) {
            // Move left pointer until a vowel is found
            while (left < right && vowels.indexOf(chars[left]) == -1) {
                left++;
            }
            // Move right pointer until a vowel is found
            while (left < right && vowels.indexOf(chars[right]) == -1) {
                right--;
            }
            
            // Swap the vowels
            if (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                
                left++;
                right--;
            }
        }
        
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(reverseVowels("leetcode")); // "leotcede"
    }
}