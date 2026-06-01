/**
 * PROBLEM: Number of Operations to Make Network Connected
 * * There are n computers numbered from 0 to n - 1 connected by ethernet cables connections.
 * You can extract redundant cables between any two connected computers and plug them between 
 * any pair of disconnected computers to make them connected.
 * Return the minimum number of times you need to do this to make all the computers connected.
 * * Strategy:
 * To connect C independent graph components together, you need exactly (C - 1) cables.
 * First, verify if total connections length >= n - 1. If not, it is mathematically impossible.
 * Then, use Union-Find to count the exact number of separated standalone networks (components).
 * * Complexity:
 * Time Complexity: O(V + E) utilizing path compression optimization.
 * Space Complexity: O(V) for the parent mapping table array.
 */
public class MakeNetworkConnected {
    static class UnionFind {
        int[] parent;
        int components;
        public UnionFind(int n) {
            parent = new int[n];
            components = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                components--; // Components shrink with every valid connection edge
            }
        }
    }

    public static int makeConnected(int n, int[][] connections) {
        // Minimum wire check for connecting n items
        if (connections.length < n - 1) return -1;
        
        UnionFind uf = new UnionFind(n);
        for (int[] conn : connections) {
            uf.union(conn[0], conn[1]);
        }
        
        return uf.components - 1;
    }

    public static void main(String[] args) {
        int[][] connections = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        System.out.println("Minimum cable moves required: " + makeConnected(6, connections)); // 2
    }
}