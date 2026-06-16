import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * PROBLEM: Find K Pairs with Smallest Sums
 * * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u, v) which consists of one element from nums1 and one element from nums2.
 * Return the k pairs with the smallest sums.
 * * Strategy: Indexed Coordinate Min-Heap
 * Push initial seed pairings tracking index allocations: `[nums1[i] + nums2[0], i, 0]` into a Min-Heap. 
 * Pop the smallest sum pair. To find subsequent candidates, advance the pointer for `nums2` 
 * by pushing `[nums1[i] + nums2[j+1], i, j+1]` back into the heap.
 * * Complexity:
 * Time Complexity: O(K log K) or O(K log N) depending on allocation boundaries.
 */
public class FindKPairsWithSmallestSums {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // Min-heap tracking: [sum, index_in_nums1, index_in_nums2]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Initialize heap seeds matching size constraints safely
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.add(new int[]{nums1[i] + nums2[0], i, 0});
        }

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] currentPair = minHeap.poll();
            int idx1 = currentPair[1];
            int idx2 = currentPair[2];

            result.add(Arrays.asList(nums1[idx1], nums2[idx2]));

            // Advance pointer downstream in nums2 if space permits
            if (idx2 + 1 < nums2.length) {
                minHeap.add(new int[]{nums1[idx1] + nums2[idx2 + 1], idx1, idx2 + 1});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] n1 = {1, 7, 11}, n2 = {2, 4, 6};
        System.out.println("3 Smallest Sum Pairs: " + kSmallestPairs(n1, n2, 3)); // [[1,2], [1,4], [1,6]]
    }
}