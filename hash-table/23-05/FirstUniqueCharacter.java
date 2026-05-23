import java.util.HashMap;

public class FirstUniqueCharacter {
    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> counts = new HashMap<>();

        // First pass: Build the frequency map
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        // Second pass: Find the first character with a count of 1
        for (int i = 0; i < s.length(); i++) {
            if (counts.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1; // No unique character found
    }

    public static void main(String[] args) {
        String text = "leetcode";
        System.out.println("String: " + text);
        
        int index = firstUniqChar(text);
        System.out.println("First unique character is '" + text.charAt(index) + "' at index: " + index);
    }
}