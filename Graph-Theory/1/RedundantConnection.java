import java.util.Arrays;

public class RedundantConnection {
    static class UnionFind {
        int[] parent;
        public UnionFind(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
        }
        
        public int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]); 
        }
        
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false; 
            parent[rootX] = rootY;
            return true;
        }
    }

    public static int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) return edge;
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println("Redundant connection: " + Arrays.toString(findRedundantConnection(edges))); // [2, 3]
    }
}