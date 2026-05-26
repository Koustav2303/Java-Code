import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SortByFrequency {
    public static String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Put keys into a list and sort them based on frequency from the map
        List<Character> chars = new ArrayList<>(map.keySet());
        chars.sort((a, b) -> map.get(b) - map.get(a));

        // Rebuild the string
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            int copies = map.get(c);
            for (int i = 0; i < copies; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "tree";
        System.out.println("Original String: " + s);
        System.out.println("Frequency Sorted: " + frequencySort(s)); 
        // e appears twice, t and r appear once: "eert" or "eetr"
    }
}