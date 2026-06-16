import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: First Unique Character in a String
 * * Given a string s, find the first non-repeating character in it and return its index. 
 * If it does not exist, return -1.
 * * Strategy: Multi-Pass Frequency Distribution
 * Run a pass over the text to record individual character counts inside a hash table array footprint. 
 * Run a second pass to identify the first character with a frequency count exactly equal to 1.
 * * Complexity:
 * Time Complexity: O(N) where N is string length.
 * Space Complexity: O(1) since the alphabet tracking array is bounded at a fixed size of 26.
 */
public class FirstUniqueChar {
    public static int firstUniqChar(String s) {
        int[] frequencyMap = new int[26]; // Bounded integer hash table array footprint
        
        // Pass 1: Populate character frequency maps
        for (int i = 0; i < s.length(); i++) {
            frequencyMap[s.charAt(i) - 'a']++;
        }
        
        // Pass 2: Identify the first unique character layout index point
        for (int i = 0; i < s.length(); i++) {
            if (frequencyMap[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String test = "leetcode";
        System.out.println("First unique character index inside '" + test + "': " + firstUniqChar(test)); // 0 ('l')
    }
}