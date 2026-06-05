import java.util.*;

/**
 * PROBLEM: Lexicographically Smallest Array by Swapping Elements
 * * Given a 0-indexed array of positive integers nums and a positive integer limit, you can swap 
 * two elements if their absolute difference is <= limit. Return the lexicographically smallest array 
 * that can be obtained by performing any number of swaps.
 * * Strategy: Delta-Chained Component Sorters
 * Sort a list of node index-value pairs by value. If adjacent values in the sorted list are within the 
 * limit, union them. This handles the transitive property of swaps.
 * Group the original positions by component root. Sort each position group to re-assign values to indices 
 * from smallest to largest.
 */
public class LexicographicallySmallestArrayDSU {
    static class ArrayDSU {
        int[] parent;
        public ArrayDSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP != rootQ) parent[rootP] = rootQ;
        }
    }

    public static int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] sortedPairs = new int[n][2]; // {value, original_index}
        for (int i = 0; i < n; i++) sortedPairs[i] = new int[]{nums[i], i};

        Arrays.sort(sortedPairs, (a, b) -> Integer.compare(a[0], b[0]));
        ArrayDSU dsu = new ArrayDSU(n);

        // Union adjacent pairs if their sorted delta difference fits within the limit
        for (int i = 1; i < n; i++) {
            if (sortedPairs[i][0] - sortedPairs[i - 1][0] <= limit) {
                dsu.union(sortedPairs[i][1], sortedPairs[i - 1][1]);
            }
        }

        // Group index locations by DSU component root
        Map<Integer, List<Integer>> indexGroups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexGroups.computeIfAbsent(dsu.find(i), x -> new ArrayList<>()).add(i);
        }

        int[] result = new int[n];
        for (List<Integer> indices : indexGroups.values()) {
            List<Integer> values = new ArrayList<>();
            for (int idx : indices) values.add(nums[idx]);
            
            Collections.sort(indices);
            Collections.sort(values);
            
            // Re-assign sorted values to the sorted indices of the component
            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = values.get(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 3, 9, 8};
        System.out.println("Lexicographically optimized outcome: " + 
            Arrays.toString(lexicographicallySmallestArray(nums, 2))); // [1, 3, 5, 8, 9]
    }
}