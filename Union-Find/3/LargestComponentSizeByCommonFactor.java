import java.util.*;

/**
 * PROBLEM: Largest Component Size by Common Factor
 * * You are given an integer array of unique positive integers nums. Consider the following graph:
 * There are nums.length nodes, labeled nums[0] to nums[nums.length - 1].
 * There is an undirected edge between nums[i] and nums[j] if they share a common factor strictly greater than 1.
 * Return the size of the largest connected component in the graph.
 * * Strategy: Prime Factorization Anchoring
 * Connecting elements directly takes $O(N^2)$ time, which is too slow. Instead, find the prime factors 
 * of each number up to its square root. Map the number's index to each of its prime factors in the DSU. 
 * Finally, count the frequencies of the root parents to locate the largest cluster group.
 */
public class LargestComponentSizeByCommonFactor {
    static class FactorDSU {
        int[] parent, size;
        public FactorDSU(int n) {
            parent = new int[n]; size = new int[n];
            for (int i = 0; i < n; i++) { parent[i] = i; size[i] = 1; }
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP != rootQ) { parent[rootP] = rootQ; size[rootQ] += size[rootP]; }
        }
    }

    public static int largestComponentSize(int[] nums) {
        int maxVal = 0;
        for (int num : nums) maxVal = Math.max(maxVal, num);

        FactorDSU dsu = new FactorDSU(maxVal + 1);

        for (int num : nums) {
            int current = num;
            // Extract prime factor 2
            if (current % 2 == 0) {
                dsu.union(num, 2);
                while (current % 2 == 0) current /= 2;
            }
            // Extract remaining odd prime factors up to the square root
            for (int i = 3; i * i <= current; i += 2) {
                if (current % i == 0) {
                    dsu.union(num, i);
                    while (current % i == 0) current /= i;
                }
            }
            if (current > 1) {
                dsu.union(num, current);
            }
        }

        Map<Integer, Integer> rootCounts = new HashMap<>();
        int maxComponentSize = 0;

        for (int num : nums) {
            int root = dsu.find(num);
            int updatedCount = rootCounts.getOrDefault(root, 0) + 1;
            rootCounts.put(root, updatedCount);
            maxComponentSize = Math.max(maxComponentSize, updatedCount);
        }
        return maxComponentSize;
    }

    public static void main(String[] args) {
        int[] nums = {4, 6, 15, 35};
        System.out.println("Max single connected component cluster size: " + largestComponentSize(nums)); // 4
    }
}