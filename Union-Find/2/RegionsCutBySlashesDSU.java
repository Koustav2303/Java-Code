/**
 * PROBLEM: Regions Cut By Slashes
 * * An n x n grid is composed of 1 x 1 squares where each square contains a '/', '\', or blank space ' '.
 * These characters divide the square into contiguous regions. Return the number of regions.
 * * Strategy: Topological Coordinate Intersect Cycle Framework
 * An N x N grid contains (N + 1) * (N + 1) grid intersection dots. Treat these dots as vertices in a graph. 
 * Connect all boundary dots to a single base node (Node 0). 
 * Each slash or backslash connects two dots on the grid mesh. 
 * If a line connects two vertices that are *already* connected in the DSU, it completes a cycle, 
 * which encloses a new standalone region.
 */
public class RegionsCutBySlashesDSU {
    static class MeshDSU {
        int[] parent;
        int regionsCount = 1; // Base grid space represents 1 starting region

        public MeshDSU(int n) {
            int dots = (n + 1) * (n + 1);
            parent = new int[dots];
            for (int i = 0; i < dots; i++) parent[i] = i;
            
            // Connect all outer border matrix coordinates to base node 0
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (i == 0 || j == 0 || i == n || j == n) {
                        int id = i * (n + 1) + j;
                        if (id != 0) parent[id] = 0;
                    }
                }
            }
        }

        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
            } else {
                regionsCount++; // A cycle is completed, forming a new region
            }
        }
    }

    public static int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int dotBound = n + 1;
        MeshDSU dsu = new MeshDSU(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char cell = grid[i].charAt(j);
                if (cell == '/') {
                    int topIdx = i * dotBound + (j + 1);
                    int bottomIdx = (i + 1) * dotBound + j;
                    dsu.union(topIdx, bottomIdx);
                } else if (cell == '\\') {
                    int topIdx = i * dotBound + j;
                    int bottomIdx = (i + 1) * dotBound + (j + 1);
                    dsu.union(topIdx, bottomIdx);
                }
            }
        }
        return dsu.regionsCount;
    }

    public static void main(String[] args) {
        String[] grid = {" /", "/ "};
        System.out.println("Total closed region count: " + regionsBySlashes(grid)); // 2
    }
}