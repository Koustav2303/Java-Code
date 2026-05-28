import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * PROBLEM: Top K Frequent Elements
 * * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * * Example:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * * Approach:
 * First, count the frequencies of all numbers using a HashMap.
 * Then, use a Min-Heap of size K to keep track of the most frequent elements.
 * The heap stores Map.Entry objects, compared by their frequency values.
 */
public class TopKFrequentElements {
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        
        // Min-Heap ordered by frequency (value of the map entry)
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
            (a, b) -> a.getValue() - b.getValue()
        );
        
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // Discard the less frequent elements
            }
        }
        
        int[] result = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll().getKey();
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println("Top " + k + " frequent: " + Arrays.toString(topKFrequent(nums, k))); // [1, 2] or [2, 1]
    }
}