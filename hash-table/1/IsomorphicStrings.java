import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Isomorphic Strings
 * * Given two strings s and t, determine if they are isomorphic. Two strings s and t are isomorphic 
 * if the characters in s can be replaced to get t, preserving original positional structures.
 * * Strategy: Dual Mapping Bijective Check
 * Maintain two separate maps to track assignments in both directions (`s -> t` and `t -> s`). 
 * Ensure every cross-character mapping behaves as a strict 1-to-1 bijection. If a character attempts 
 * to map to multiple different targets, the strings are not isomorphic.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(K) where K is individual character set boundaries pool.
 */
public class IsomorphicStrings {
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> mapStoT = new HashMap<>();
        Map<Character, Character> mapTtoS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Verify mapping consistency from s to t
            if (mapStoT.containsKey(charS)) {
                if (mapStoT.get(charS) != charT) return false;
            } else {
                mapStoT.put(charS, charT);
            }

            // Verify reverse mapping consistency from t to s
            if (mapTtoS.containsKey(charT)) {
                if (mapTtoS.get(charT) != charS) return false;
            } else {
                mapTtoS.put(charT, charS);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Are 'egg' and 'add' isomorphic? " + isIsomorphic("egg", "add")); // true
        System.out.println("Are 'foo' and 'bar' isomorphic? " + isIsomorphic("foo", "bar")); // false
    }
}