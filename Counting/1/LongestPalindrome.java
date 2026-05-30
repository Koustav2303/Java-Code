/**
 * PROBLEM: Longest Palindrome
 * * Given a string s which consists of lowercase or uppercase letters, return the length 
 * of the longest palindrome that can be built with those letters.
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 * * Example:
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 * * Approach:
 * Count the frequencies of all characters.
 * For any character with an even count, we can use all of them.
 * For any character with an odd count, we can use (count - 1) of them.
 * Finally, if we have ANY odd character left over, we can place exactly one of them perfectly in the middle.
 */
public class LongestPalindrome {
    public static int longestPalindrome(String s) {
        int[] charCounts = new int[128]; // Fits all standard ASCII
        for (char c : s.toCharArray()) {
            charCounts[c]++;
        }
        
        int length = 0;
        boolean hasOdd = false;
        
        for (int count : charCounts) {
            // Add the even part of the count
            length += (count / 2) * 2;
            
            // Mark if there's any odd character
            if (count % 2 == 1) {
                hasOdd = true;
            }
        }
        
        // We can place exactly one odd character in the very center of the palindrome
        if (hasOdd) {
            length++;
        }
        
        return length;
    }

    public static void main(String[] args) {
        System.out.println("Longest palindrome length for 'abccccdd': " + longestPalindrome("abccccdd")); // 7
    }
}