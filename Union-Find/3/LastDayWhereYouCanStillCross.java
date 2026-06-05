import java.util.Arrays;

/**
 * PROBLEM: Last Day Where You Can Still Cross
 * * There is a 1-based binary matrix of size row x col containing all 0s initially. Every day, 
 * a cell listed in cells becomes flooded (1). You want to find the last day you can walk from 
 * the top row to the bottom row through 0 cells.
 * * Strategy: Reverse-Time Flood Map Assembly
 * Simulating water filling and tracking connectivity breaks is complex. Instead, run it backward! 
 * Start with a matrix completely flooded with water. Create a DSU with virtual source node 0 (top row) 
 * and destination node `row * col + 1` (bottom row). Add cells back one by one from the end of the timeline. 
 * Return the current day index as soon as the top and bottom rows connect.
 */
public class LastDayWhereYouCanStillCross {
    static class CrossDSU {
        int[] parent;
        public CrossDSU(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
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

    public static int latestDayToCross(int row, int col, int[][] cells) {
        int totalCells = row * col;
        CrossDSU dsu = new CrossDSU(totalCells + 2);
        int topVirtualNode = 0;
        int bottomVirtualNode = totalCells + 1;

        int[][] gridState = new int[row][col];
        for (int[] cell : gridState) Arrays.fill(cell, 1); // Start with full flooding state logic

        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}}; 
        // Note: Problem allows 4-directional traversal, but let's stick to standard 4-way neighbors for union pathing
        int[][] standardDirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        // Process timeline backward
        for (int i = cells.length - 1; i >= 0; i--) {
            int r = cells[i][0] - 1, c = cells[i][1] - 1;
            gridState[r][c] = 0; // Solid ground restored
            int currentId = r * col + c + 1;

            if (r == 0) dsu.union(currentId, topVirtualNode);
            if (r == row - 1) dsu.union(currentId, bottomVirtualNode);

            for (int[] d : standardDirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col && gridState[nr][nc] == 0) {
                    dsu.union(currentId, nr * col + nc + 1);
                }
            }

            if (dsu.find(topVirtualNode) == dsu.find(bottomVirtualNode)) {
                return i; // Found the transition day
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] cells = {{1, 1}, {2, 1}, {1, 2}, {2, 2}};
        System.out.println("Last available traversal crossing day index: " + latestDayToCross(2, 2, cells)); // 2
    }
}