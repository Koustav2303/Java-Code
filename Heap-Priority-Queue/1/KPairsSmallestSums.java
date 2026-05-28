/**
 * PROBLEM: Find K Pairs with Smallest Sums
 * * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
 * Define a pair (u, v) which consists of one element from the first array and one element from the second.
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 * * Example:
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * * Approach:
 * Use a Min-Heap storing arrays of size 3: [sum, indexInNums1, indexInNums2].
 * Initialize the heap with pairs of (nums1[i], nums2[0]). 
 * Then, extract the minimum, add it to the result, and push the next pair (nums1[i], nums2[j+1]) into the heap.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KPairsSmallestSums {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;
        
        // Min-Heap: [sum, i, j]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.add(new int[]{nums1[i] + nums2[0], i, 0});
        }
        
        while (k > 0 && !minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int i = curr[1], j = curr[2];
            
            result.add(Arrays.asList(nums1[i], nums2[j]));
            
            if (j + 1 < nums2.length) {
                minHeap.add(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
            k--;
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;
        System.out.println("K smallest pairs: " + kSmallestPairs(nums1, nums2, k));
    }
}