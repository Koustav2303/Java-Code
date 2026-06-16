import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PROBLEM: Group Anagrams
 * * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * * Strategy: Character Profile Canonical Invariant Keys
 * Anagrams share identical character profiles. Iterate through strings, sorting each string's characters 
 * alphabetically to create a canonical invariant key. Map each unique sorted key string to a collection list 
 * containing its original un-sorted structural source words.
 * * Complexity:
 * Time Complexity: O(N * M log M) where N is array size and M is individual word length.
 * Space Complexity: O(N * M)
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        
        Map<String, List<String>> anagramClusterMap = new HashMap<>();

        for (String word : strs) {
            char[] charactersArray = word.toCharArray();
            Arrays.sort(charactersArray); // Establish canonical sorted key representation
            String canonicalKey = new String(charactersArray); // Fixed constructor call here

            if (!anagramClusterMap.containsKey(canonicalKey)) {
                anagramClusterMap.put(canonicalKey, new ArrayList<>());
            }
            anagramClusterMap.get(canonicalKey).add(word);
        }
        return new ArrayList<>(anagramClusterMap.values());
    }

    public static void main(String[] args) {
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Anagram clusters allocation:\n" + groupAnagrams(words));
    }
}