import java.util.PriorityQueue;

/**
 * PROBLEM: Reorganize String
 * * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * Return any possible rearrangement of s or return an empty string "" if it is impossible.
 * * Strategy: Interleaved Max-Heap Character Matching
 * Count character frequencies and push them into a Max-Heap. Pop the character with the highest frequency 
 * to build the string. To prevent adjacent duplicates, cache this character and use the *second* highest frequency 
 * character for the next position. Push the cached character back into the heap once a safe buffer gap is established.
 * * Complexity:
 * Time Complexity: O(N log A) where A is alphabet pool size (constant O(1) space constraints since A <= 26).
 */
public class ReorganizeString {
    public static String reorganizeString(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) counts[c - 'a']++;

        // Max-heap prioritizing highest character count frequencies descending
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                // If a single character's frequency exceeds the theoretical maximum safe threshold, it's impossible
                if (counts[i] > (s.length() + 1) / 2) return "";
                maxHeap.add(new int[]{i + 'a', counts[i]});
            }
        }

        StringBuilder sb = new StringBuilder();
        int[] blockCache = null;

        while (!maxHeap.isEmpty()) {
            int[] current = maxHeap.poll();
            sb.append((char) current[0]);
            current[1]--;

            // Re-enqueue the previously cached character back into the active pool
            if (blockCache != null && blockCache[1] > 0) {
                maxHeap.add(blockCache);
            }

            // Cache the current character to prevent it from being picked for the immediate next position
            blockCache = current;
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println("Reorganized 'aab': " + reorganizeString("aab")); // "aba"
        System.out.println("Reorganized 'aaab': " + reorganizeString("aaab")); // ""
    }
}