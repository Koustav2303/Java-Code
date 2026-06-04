import java.util.*;

/**
 * PROBLEM: Longest Consecutive Sequence
 * * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(N) time.
 * * Strategy: Dynamic Parent Map Clustering
 * Initialize each number as a disjoint element inside a HashMap tracking component sizes. 
 * For each unique value `num`, check if `num - 1` or `num + 1` exists in the dataset. 
 * If present, execute a union operation and track the maximum component size generated dynamically.
 */
public class LongestConsecutiveDSU {
    static class UnionFind {
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> size = new HashMap<>();
        int maxLen = 0;

        public void insert(int val) {
            if (parent.containsKey(val)) return;
            parent.put(val, val);
            size.put(val, 1);
            maxLen = Math.max(maxLen, 1);
        }

        public int find(int i) {
            if (parent.get(i) == i) return i;
            parent.put(i, find(parent.get(i))); // Path compression
            return parent.get(i);
        }

        public void union(int p, int q) {
            if (!parent.containsKey(p) || !parent.containsKey(q)) return;
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent.put(rootP, rootQ);
                int newSize = size.get(rootQ) + size.get(rootP);
                size.put(rootQ, newSize);
                maxLen = Math.max(maxLen, newSize);
            }
        }
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        UnionFind uf = new UnionFind();

        for (int num : nums) uf.insert(num);
        for (int num : nums) {
            uf.union(num, num - 1);
            uf.union(num, num + 1);
        }
        return uf.maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println("Longest consecutive sequence length: " + longestConsecutive(nums)); // 4 (1,2,3,4)
    }
}