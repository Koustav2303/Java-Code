import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PROBLEM: Sort Characters By Frequency
 * * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * * Example:
 * Input: s = "tree"
 * Output: "eert" (or "eetr")
 * * Approach:
 * 1. Count character frequencies using a HashMap.
 * 2. Use Bucket Sort. Create an array of Lists where the index represents the frequency.
 * 3. Read from the end of the bucket array to build the most frequent characters first.
 */
public class SortCharactersByFrequency {
    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        // Buckets where index = frequency
        List<Character>[] buckets = new List[s.length() + 1];
        for (Character key : map.keySet()) {
            int frequency = map.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }
        
        StringBuilder sb = new StringBuilder();
        // Traverse backwards to get highest frequency first
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Sorted 'tree': " + frequencySort("tree")); // eert
    }
}