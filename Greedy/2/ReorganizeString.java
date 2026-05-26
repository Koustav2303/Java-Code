import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {
    public static String reorganizeString(String s) {
        HashMap<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) counts.put(c, counts.getOrDefault(c, 0) + 1);

        // Max-Heap sorted by character frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );
        maxHeap.addAll(counts.entrySet());

        StringBuilder result = new StringBuilder();
        Map.Entry<Character, Integer> previous = null;

        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> current = maxHeap.poll();
            result.append(current.getKey());
            current.setValue(current.getValue() - 1); // Decrease count

            // Put the previous character back into the heap if it still has remaining counts
            if (previous != null && previous.getValue() > 0) {
                maxHeap.add(previous);
            }
            
            // Hold the current character out for the next turn
            previous = current;
        }

        // If the result string length doesn't match, it's impossible to reorganize
        return result.length() == s.length() ? result.toString() : "";
    }

    public static void main(String[] args) {
        String s = "aab";
        System.out.println("Original: " + s);
        System.out.println("Reorganized: " + reorganizeString(s));
    }
}