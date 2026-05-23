import java.util.HashSet;

public class LongestPalindrome {
    public static int longestPalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        int length = 0;

        for (char c : s.toCharArray()) {
            // If the set already has the char, we found a pair!
            if (set.contains(c)) {
                length += 2;      // Add the pair to our total length
                set.remove(c);    // Remove the pair from the set
            } else {
                set.add(c);       // Unpaired char, add to set
            }
        }

        // If there is any unpaired character left in the set, 
        // we can place exactly one in the absolute center of the palindrome.
        if (!set.isEmpty()) {
            length += 1;
        }

        return length;
    }

    public static void main(String[] args) {
        String letters = "abccccdd";
        System.out.println("Given string: " + letters);
        System.out.println("Longest palindrome you can build: " + longestPalindrome(letters) + " characters");
        // Explanation: You can build "dccaccd" (length 7)
    }
}