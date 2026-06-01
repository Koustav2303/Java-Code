/**
 * PROBLEM: Graph Valid Tree
 * * Given n nodes labeled from 0 to n - 1 and a list of undirected edges, 
 * write a function to check whether these edges make up a valid tree topology.
 * * Core Insight:
 * An undirected graph is a valid tree if and only if:
 * 1. It has exactly n - 1 edges.
 * 2. It contains no loops or isolated cycles and is completely unified.
 * We can run Union-Find. If union returns false for any edge before the end, 
 * a cycle exists, and it cannot be a tree.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class GraphValidTree {
    public static boolean validTree(int n, int[][] edges) {
        // Rule 1: A tree must have exactly n - 1 edges
        if (edges.length != n - 1) return false;
        
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        for (int[] edge : edges) {
            int rootX = find(parent, edge[0]);
            int rootY = find(parent, edge[1]);
            
            if (rootX == rootY) {
                return false; // Cycle loop detected!
            }
            parent[rootX] = rootY;
        }
        return true;
    }
    
    private static int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent, parent[i]);
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println("Is graph a valid tree structural link? " + validTree(5, edges)); // true
    }
}