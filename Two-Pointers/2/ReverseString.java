/**
 * PROBLEM: Reverse String
 * * Write a function that reverses a string. The input string is given as an array of characters s.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 * * Example:
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class ReverseString {
    public static void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        
        // Swap characters from the outside in
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        System.out.println(s); // olleh
    }
}