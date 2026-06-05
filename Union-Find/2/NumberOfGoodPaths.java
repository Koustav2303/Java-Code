import java.util.*;

/**
 * PROBLEM: Number of Good Paths
 * * There is a tree consisting of n nodes. You are given an integer array vals of length n, 
 * where vals[i] denotes the value of the ith node. A good path is a simple path where the starting 
 * and ending nodes have the same value, and all nodes on the path have values <= the terminal value.
 * Return the number of distinct good paths.
 * * Strategy: Value-Driven Map Backplane Combinatorics
 * Map nodes into a values lookup matrix and sort values in ascending order. Process edges 
 * by checking node thresholds. Maintain a count map inside each component tracking the frequency 
 * of the maximum values currently inside that set. When unioning two groups that share the same max value, 
 * multiply their frequencies to find the number of valid crossing paths: paths += count1 * count2.
 */
public class NumberOfGoodPaths {
    static class GoodPathsDSU {
        int[] parent;
        Map<Integer, Integer>[] valueCountMap; // Array of Maps tracking {Value : Frequency} inside component

        @SuppressWarnings("unchecked")
        public GoodPathsDSU(int n, int[] vals) {
            parent = new int[n];
            valueCountMap = new Map[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                valueCountMap[i] = new HashMap<>();
                valueCountMap[i].put(vals[i], 1);
            }
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
    }

    public static int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Group node indices by their raw threshold value properties
        TreeMap<Integer, List<Integer>> valueNodesMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            valueNodesMap.computeIfAbsent(vals[i], x -> new ArrayList<>()).add(i);
        }

        GoodPathsDSU dsu = new GoodPathsDSU(n, vals);
        int goodPathsCount = n; // Single nodes are implicitly valid baseline paths

        // Iterate through node groups in ascending order of value thresholds
        for (int value : valueNodesMap.keySet()) {
            for (int node : valueNodesMap.get(value)) {
                for (int neighbor : adj.get(node)) {
                    // Only bridge to neighbors whose values are <= the current value
                    if (vals[neighbor] <= value) {
                        int rootNode = dsu.find(node);
                        int rootNeighbor = dsu.find(neighbor);
                        
                        if (rootNode != rootNeighbor) {
                            int countNode = dsu.valueCountMap[rootNode].getOrDefault(value, 0);
                            int countNeighbor = dsu.valueCountMap[rootNeighbor].getOrDefault(value, 0);
                            
                            goodPathsCount += countNode * countNeighbor; // Combinatorics accumulation
                            
                            // Union step: merge components and update structural frequencies
                            dsu.parent[rootNode] = rootNeighbor;
                            dsu.valueCountMap[rootNeighbor].put(value, countNode + countNeighbor);
                        }
                    }
                }
            }
        }
        return goodPathsCount;
    }

    public static void main(String[] args) {
        int[] vals = {1, 3, 2, 1, 3};
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}};
        System.out.println("Total valid good paths: " + numberOfGoodPaths(vals, edges)); // 6
    }
}