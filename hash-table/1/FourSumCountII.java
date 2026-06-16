import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: 4Sum II
 * * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number 
 * of tuples (i, j, k, l) such that nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0.
 * * Strategy: Multi-Array Complement Split Matrix
 * Processing 4 arrays inside a nested loop takes O(N^4) time. Instead, split the problem in half. 
 * Group and map all pairwise sum combinations of `nums1` and `nums2` inside a frequency map. 
 * Then loop through `nums3` and `nums4`, looking for the negative complement of their sum in the map.
 * * Complexity:
 * Time Complexity: O(N^2)
 * Space Complexity: O(N^2) worst-case pair sum storage footprint.
 */
public class FourSumCountII {
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> pairSumFrequencyMap = new HashMap<>();
        int tupleCombinationsCount = 0;

        // Pass 1: Map all pairwise sum configurations of the first two arrays
        for (int a : nums1) {
            for (int b : nums2) {
                int sum = a + b;
                pairSumFrequencyMap.put(sum, pairSumFrequencyMap.getOrDefault(sum, 0) + 1);
            }
        }

        // Pass 2: Identify complements using combinations from the remaining two arrays
        for (int c : nums3) {
            for (int d : nums4) {
                int targetComplement = -(c + d);
                tupleCombinationsCount += pairSumFrequencyMap.getOrDefault(targetComplement, 0);
            }
        }
        return tupleCombinationsCount;
    }

    public static void main(String[] args) {
        int[] n1 = {1, 2}, n2 = {-2, -1}, n3 = {-1, 2}, n4 = {0, 2};
        System.out.println("Valid zero-sum tuples count: " + fourSumCount(n1, n2, n3, n4)); // 2
    }
}