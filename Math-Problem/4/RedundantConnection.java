import java.util.Arrays;

public class RedundantConnection {
    public static int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        // Initially, every node is its own parent
        for (int i = 0; i < parent.length; i++) parent[i] = i;
        
        for (int[] edge : edges) {
            int root1 = find(parent, edge[0]);
            int root2 = find(parent, edge[1]);
            
            // If they have the same root, adding this edge creates a cycle
            if (root1 == root2) {
                return edge;
            }
            // Otherwise, join them
            parent[root1] = root2;
        }
        return new int[0];
    }

    private static int find(int[] parent, int node) {
        if (parent[node] == node) {
            return node;
        }
        // Path compression
        return parent[node] = find(parent, parent[node]);
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2}, {1,3}, {2,3}};
        System.out.println("Redundant edge: " + Arrays.toString(findRedundantConnection(edges)));
    }
}