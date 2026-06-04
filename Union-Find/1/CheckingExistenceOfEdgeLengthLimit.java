import java.util.*;

/**
 * PROBLEM: Checking Existence of Edge Length Limited Paths
 * * An undirected graph of n nodes is represented by a 2D integer array edgeList, where edgeList[i] = [ui, vi, disi].
 * You are given an array queries where queries[j] = [pj, qj, limitj].
 * Return a boolean array answer where answer[j] is true if there is a path between pj and qj such that 
 * every edge on the path has a distance strictly less than limitj.
 * * Strategy: Offline Sort-Sweep Processing
 * Processing this online is too slow. Instead, use an offline approach: sort both the edge list and the queries array 
 * by distance/limit constraints in ascending order. Sweep through the queries, using a two-pointer approach to 
 * union all edges smaller than the current query's limit into the DSU before evaluating connectivity.
 */
public class CheckingExistenceOfEdgeLengthLimit {
    static class BasicDSU {
        int[] parent;
        public BasicDSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) parent[rootP] = rootQ;
        }
    }

    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        // Store queries with original index parameters to restore ordering layout at output
        int[][] sortedQueries = new int[queries.length][4];
        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i] = new int[]{queries[i][0], queries[i][1], queries[i][2], i};
        }

        Arrays.sort(edgeList, (a, b) -> Integer.compare(a[2], b[2]));
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[2], b[2]));

        BasicDSU dsu = new BasicDSU(n);
        boolean[] result = new boolean[queries.length];
        int edgeIdx = 0;

        for (int[] query : sortedQueries) {
            int p = query[0], q = query[1], limit = query[2], originalIdx = query[3];

            // Union all graph edges strictly smaller than current threshold limit value
            while (edgeIdx < edgeList.length && edgeList[edgeIdx][2] < limit) {
                dsu.union(edgeList[edgeIdx][0], edgeList[edgeIdx][1]);
                edgeIdx++;
            }

            result[originalIdx] = (dsu.find(p) == dsu.find(q));
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] edgeList = {{0, 1, 2}, {1, 2, 4}, {2, 0, 8}, {1, 0, 16}};
        int[][] queries = {{0, 2, 5}, {0, 2, 3}};
        System.out.println("Queries existence analysis matrix outputs: " + 
            Arrays.toString(distanceLimitedPathsExist(3, edgeList, queries))); // [true, false]
    }
}