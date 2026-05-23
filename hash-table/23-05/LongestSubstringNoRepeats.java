import java.util.HashMap;

public class LongestSubstringNoRepeats {
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If we've seen the character, move the left pointer past its last occurrence
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }

            // Update the map with the current index
            map.put(currentChar, right);
            // Calculate the max length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String text = "abcabcbb";
        System.out.println("String: " + text);
        System.out.println("Length of longest substring without repeats: " + lengthOfLongestSubstring(text));
    }
}