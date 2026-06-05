import java.util.Arrays;

/**
 * PROBLEM: Bricks Falling When Hit
 * * You are given an m x n binary grid, where 1 represents a brick and 0 represents an empty space. 
 * A brick is stable if it is connected to the top row, or 4-directionally adjacent to another stable brick.
 * You are also given an array hits. Erase bricks at hits coordinates sequentially. Return an array 
 * tracking how many bricks fall after each individual hit.
 * * Strategy: Reverse-Time Offline Accumulation
 * Calculating falling bricks forward is hard because structures break apart. Instead, process it backward!
 * Remove all hit bricks from the grid first. Initialize a DSU with a virtual roof node at index 0. 
 * Run the initial DSU on remaining bricks. Then, add hit bricks back in REVERSE order. 
 * The change in the size of the component connected to the roof tells us exactly how many bricks fell.
 */
public class BricksFallingWhenHit {
    static class BrickDSU {
        int[] parent, size;
        public BrickDSU(int n) {
            parent = new int[n]; size = new int[n];
            for (int i = 0; i < n; i++) { parent[i] = i; size[i] = 1; }
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
        }
        public int getRoofSize() {
            return size[find(0)]; // Roof is mapped to index 0
        }
    }

    public static int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length, n = grid[0].length;
        int[][] currentGrid = new int[m][n];
        for (int i = 0; i < m; i++) currentGrid[i] = grid[i].clone();

        // Step 1: Erase all hit brick instances from the workspace completely
        for (int[] hit : hits) {
            currentGrid[hit[0]][hit[1]] = 0;
        }

        BrickDSU dsu = new BrickDSU(m * n + 1);
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        // Step 2: Establish base baseline connectivity on the modified grid
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (currentGrid[r][c] == 1) {
                    int id = r * n + c + 1;
                    if (r == 0) dsu.union(id, 0); // Connect top row directly to the roof (0)
                    if (r - 1 >= 0 && currentGrid[r - 1][c] == 1) dsu.union(id, (r - 1) * n + c + 1);
                    if (c - 1 >= 0 && currentGrid[r][c - 1] == 1) dsu.union(id, r * n + (c - 1) + 1);
                }
            }
        }

        int[] result = new int[hits.length];
        
        // Step 3: Iterate backwards through the execution history array
        for (int i = hits.length - 1; i >= 0; i--) {
            int hr = hits[i][0], hc = hits[i][1];
            if (grid[hr][hc] == 0) continue; // Hit missed a brick originally

            int preRoofSize = dsu.getRoofSize();
            int id = hr * n + hc + 1;

            if (hr == 0) dsu.union(id, 0); // Attach to roof if added back to top row

            for (int[] d : dirs) {
                int nr = hr + d[0], nc = hc + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && currentGrid[nr][nc] == 1) {
                    dsu.union(id, nr * n + nc + 1);
                }
            }

            int postRoofSize = dsu.getRoofSize();
            currentGrid[hr][hc] = 1; // Restore cell state back completely

            // If the size grew, the net increase (minus the restored brick itself) is the number of fallen bricks
            if (dsu.find(id) == dsu.find(0)) {
                result[i] = Math.max(0, postRoofSize - preRoofSize - 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {1, 1, 1, 0}};
        int[][] hits = {{1, 0}};
        System.out.println("Fallen bricks tracking trace: " + Arrays.toString(hitBricks(grid, hits))); // [2]
    }
}