import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * PROBLEM: Top K Frequent Elements
 * * Given an integer array nums and an integer k, return the k most frequent elements. 
 * You may return the answer in any order.
 * * Strategy: Map Counter Bounded Min-Heap
 * Count frequency mappings inside a HashMap. Maintain a Min-Heap tracking keys sorted by their 
 * frequency values. Keep the heap size bounded to at most `k`. After processing all unique numbers, 
 * the heap will contain exactly the top $k$ most frequent elements.
 * * Complexity:
 * Time Complexity: O(N log K)
 * Space Complexity: O(N) mapping structures.
 */
public class TopKFrequentElements {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Min-heap tracking keys sorted by frequency values ascending
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(freqMap.get(a), freqMap.get(b))
        );

        for (int key : freqMap.keySet()) {
            minHeap.add(key);
            if (minHeap.size() > k) {
                minHeap.poll(); // Evict elements with smaller frequencies outside top-K window
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println("Top 2 frequent elements array: " + java.util.Arrays.toString(topKFrequent(nums, 2))); // [2, 1]
    }
}