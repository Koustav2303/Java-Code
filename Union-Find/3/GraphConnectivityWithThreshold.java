import java.util.*;

/**
 * PROBLEM: Graph Connectivity With Threshold
 * * We have n cities labeled from 1 to n. Two cities x and y are connected if they share a common divisor 
 * strictly greater than a given threshold. Given queries, return whether a path exists between them.
 * * Strategy: Sieve-Based Factor Connection
 * Loop through all values from `threshold + 1` up to `n`. For each factor, find all of its multiples 
 * via step-multiplication loops (`m = 2 * factor`, `3 * factor`, ...) and union them together. 
 * This structures the connectivity matrix in $O(N \log N)$ time, allowing each query to be answered in $O(1)$.
 */
public class GraphConnectivityWithThreshold {
    static class SieveDSU {
        int[] parent;
        public SieveDSU(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
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

    public static List<Boolean> questionnairesProcessor(int n, int threshold, int[][] queries) {
        SieveDSU dsu = new SieveDSU(n);

        // Connect multiples using a sieve-like pattern
        for (int factor = threshold + 1; factor <= n; factor++) {
            for (int multiple = 2 * factor; multiple <= n; multiple += factor) {
                dsu.union(factor, multiple);
            }
        }

        List<Boolean> answers = new ArrayList<>();
        for (int[] query : queries) {
            answers.add(dsu.find(query[0]) == dsu.find(query[1]));
        }
        return answers;
    }

    public static void main(String[] args) {
        int[][] queries = {{1, 4}, {2, 5}, {6, 12}};
        // Threshold 2 means common factor must be > 2. 6 and 12 share factor 6, so they are connected.
        System.out.println("Query existence array verification: " + questionnairesProcessor(12, 2, queries)); 
        // [false, false, true]
    }
}