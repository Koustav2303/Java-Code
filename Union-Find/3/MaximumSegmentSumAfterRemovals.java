import java.util.Arrays;

/**
 * PROBLEM: Maximum Segment Sum After Removals
 * * You are given two 0-indexed integer arrays nums and removeQueries, both of length n. 
 * For each query, the element at removeQueries[i] is removed. Return an array where answer[i] 
 * is the maximum segment sum of contiguous elements after the ith removal.
 * * Strategy: Backward Restoration Map
 * Run the process backward. Start with an empty array. Add elements back one by one in the reverse 
 * order of `removeQueries`. When an element is restored, calculate its initial sum and check its left 
 * and right neighbors. If they are also active, union them and merge their segment sums. 
 * Track the maximum segment sum dynamically.
 */
public class MaximumSegmentSumAfterRemovals {
    static class SegmentDSU {
        int[] parent;
        long[] segmentSum;
        public SegmentDSU(int n, int[] nums) {
            parent = new int[n]; segmentSum = new long[n];
            for (int i = 0; i < n; i++) { parent[i] = i; segmentSum[i] = nums[i]; }
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                segmentSum[rootQ] += segmentSum[rootP]; // Merge segment sums
            }
        }
    }

    public static long[] maxSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        SegmentDSU dsu = new SegmentDSU(n, nums);
        boolean[] active = new boolean[n];
        long[] result = new long[n];
        long currentMax = 0;

        // Process queries backward
        for (int i = n - 1; i >= 0; i--) {
            result[i] = currentMax; // Max sum *after* current removal step is the running max before addition
            int idx = removeQueries[i];
            active[idx] = true;

            if (idx - 1 >= 0 && active[idx - 1]) dsu.union(idx, idx - 1);
            if (idx + 1 < n && active[idx + 1]) dsu.union(idx, idx + 1);

            currentMax = Math.max(currentMax, dsu.segmentSum[dsu.find(idx)]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 6, 1};
        int[] removeQueries = {0, 3, 2, 4, 1};
        System.out.println("Contiguous maximum segment sums timeline: " + Arrays.toString(maxSegmentSum(nums, removeQueries)));
        // [14, 7, 2, 2, 0]
    }
}