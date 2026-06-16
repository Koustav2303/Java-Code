import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Longest Substring Without Repeating Characters
 * * Given a string s, find the length of the longest substring without repeating characters.
 * * Strategy: Sliding Window Location Memory
 * Use a dynamic sliding window bounded by pointers `left` and `right`. Maintain a HashMap to store 
 * each character along with its most recent index position. When a duplicate character is found 
 * inside the active window, update the `left` boundary to skip past the duplicate's previous index.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(Min(M, K)) where M is string length and K is character set size.
 */
public class LongestSubstringWithoutRepeating {
    public static int lengthOfLongestSubstring(String s) {
        int maximumSubstringLength = 0;
        int leftWindowPointer = 0;
        Map<Character, Integer> characterPositionMemoryMap = new HashMap<>();

        for (int rightWindowPointer = 0; rightWindowPointer < s.length(); rightWindowPointer++) {
            char currentCharacter = s.charAt(rightWindowPointer);

            // If the character is already inside the active window, jump the left boundary forward
            if (characterPositionMemoryMap.containsKey(currentCharacter)) {
                leftWindowPointer = Math.max(leftWindowPointer, characterPositionMemoryMap.get(currentCharacter) + 1);
            }

            characterPositionMemoryMap.put(currentCharacter, rightWindowPointer);
            maximumSubstringLength = Math.max(maximumSubstringLength, rightWindowPointer - leftWindowPointer + 1);
        }
        return maximumSubstringLength;
    }

    public static void main(String[] args) {
        String test = "abcabcbb";
        System.out.println("Max continuous unique string width: " + lengthOfLongestSubstring(test)); // 3 ("abc")
    }
}