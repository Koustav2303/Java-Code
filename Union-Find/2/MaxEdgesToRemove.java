/**
 * PROBLEM: Remove Max Number of Edges to Keep Graph Fully Traversable
 * * Given an undirected graph of n nodes and three edge types: Type 1 (Alice only), 
 * Type 2 (Bob only), Type 3 (Both). Find the maximum number of edges you can remove 
 * such that the graph remains fully traversable for both Alice and Bob.
 * * Strategy: Priority Shared-Edge Processing
 * Use two separate DSU structures for Alice and Bob. Greedily process Type 3 edges first, 
 * as they benefit both graphs simultaneously. If a Type 3 edge connects disconnected components, 
 * union it in both DSUs. Then process type 1 and 2 edges independently. If any element fails a union check, 
 * it is redundant and can be removed.
 */
public class MaxEdgesToRemove {
    static class ParallelDSU {
        int[] parent;
        int components;
        public ParallelDSU(int n) {
            parent = new int[n + 1]; components = n;
            for (int i = 1; i <= n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public boolean union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP != rootQ) { parent[rootP] = rootQ; components--; return true; }
            return false;
        }
    }

    public static int maxEdgesToRemove(int n, int[][] edges) {
        ParallelDSU alice = new ParallelDSU(n);
        ParallelDSU bob = new ParallelDSU(n);
        int elementsConsumed = 0;

        // Pass 1: Shared Type 3 elements prioritize execution scaling
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                boolean uA = alice.union(edge[1], edge[2]);
                boolean uB = bob.union(edge[1], edge[2]);
                if (uA || uB) elementsConsumed++;
            }
        }

        // Pass 2: Isolated user edge types processed independently
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (alice.union(edge[1], edge[2])) elementsConsumed++;
            } else if (edge[0] == 2) {
                if (bob.union(edge[1], edge[2])) elementsConsumed++;
            }
        }

        if (alice.components != 1 || bob.components != 1) return -1;
        return edges.length - elementsConsumed;
    }

    public static void main(String[] args) {
        int[][] edges = {
            {3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {2, 1, 3}
        };
        System.out.println("Maximum cleanable redundant edge footprint count: " + maxEdgesToRemove(3, edges)); // 1
    }
}