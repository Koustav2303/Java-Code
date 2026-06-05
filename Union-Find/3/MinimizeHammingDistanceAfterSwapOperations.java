import java.util.*;

/**
 * PROBLEM: Minimize Hamming Distance After Swap Operations
 * * You are given two integer arrays source and target, both of length n. You are also given an array 
 * allowedSwaps where allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements 
 * at index ai and index bi. The Hamming distance is the number of positions where source[i] != target[i].
 * Return the minimum Hamming distance between source and target after any number of swap operations.
 * * Strategy: Multiset Index Alignment
 * Indices that can be swapped form connected components. Find these components using Union-Find. 
 * For each independent component, collect all corresponding values from `source` into a frequency map. 
 * Iterate through the indices of that component, check if the value from `target` exists in the map, 
 * decrement its count if it does, and accumulate matches. 
 * Minimum Hamming Distance = Total Elements - Matches Found.
 */
public class MinimizeHammingDistanceAfterSwapOperations {
    static class SwapDSU {
        int[] parent;
        public SwapDSU(int n) {
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

    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        SwapDSU dsu = new SwapDSU(n);

        for (int[] swap : allowedSwaps) {
            dsu.union(swap[0], swap[1]);
        }

        // Group array indices by their component root
        Map<Integer, Map<Integer, Integer>> componentValueFreqMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            componentValueFreqMap.computeIfAbsent(root, x -> new HashMap<>());
            Map<Integer, Integer> freq = componentValueFreqMap.get(root);
            freq.put(source[i], freq.getOrDefault(source[i], 0) + 1);
        }

        int matches = 0;
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            Map<Integer, Integer> freq = componentValueFreqMap.get(root);
            
            if (freq.containsKey(target[i]) && freq.get(target[i]) > 0) {
                matches++;
                freq.put(target[i], freq.get(target[i]) - 1);
            }
        }
        return n - matches;
    }

    public static void main(String[] args) {
        int[] source = {1, 2, 3, 4}, target = {2, 1, 4, 5};
        int[][] allowedSwaps = {{0, 1}, {2, 3}};
        System.out.println("Minimum possible Hamming distance: " + minimumHammingDistance(source, target, allowedSwaps)); // 1
    }
}