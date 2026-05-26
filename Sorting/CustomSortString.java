import java.util.HashMap;

public class CustomSortString {
    public static String customSortString(String order, String s) {
        HashMap<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        StringBuilder result = new StringBuilder();
        
        // 1. Append characters in the exact order they appear in 'order'
        for (char c : order.toCharArray()) {
            if (counts.containsKey(c)) {
                int frequency = counts.get(c);
                for (int i = 0; i < frequency; i++) result.append(c);
                counts.remove(c); // Remove from map once processed
            }
        }
        
        // 2. Append any leftover characters that weren't in 'order'
        for (char c : counts.keySet()) {
            int frequency = counts.get(c);
            for (int i = 0; i < frequency; i++) result.append(c);
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        String order = "cba";
        String s = "abcd";
        System.out.println("Order: " + order + " | String: " + s);
        System.out.println("Custom Sorted: " + customSortString(order, s)); // Expected: "cbad"
    }
}