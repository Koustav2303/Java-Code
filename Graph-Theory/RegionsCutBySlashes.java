/**
 * PROBLEM: Regions Cut By Slashes
 * * An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '.
 * These characters divide the square into contiguous regions. Return the number of regions.
 * * Approach:
 * Graph Scaling. A slash '/' inside a 1x1 cell is hard to process. So we scale the 1x1 grid into a 3x3 grid!
 * '/' becomes a diagonal of 1s in the 3x3 grid.
 * '\' becomes the opposite diagonal of 1s.
 * Once the grid is upscaled to 3N x 3N, finding regions is just a simple DFS counting "0" islands.
 */
public class RegionsCutBySlashes {
    public static int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int[][] expanded = new int[n * 3][n * 3];
        
        // Scale 1x1 to 3x3
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int r = i * 3, c = j * 3;
                if (grid[i].charAt(j) == '/') {
                    expanded[r][c + 2] = 1;
                    expanded[r + 1][c + 1] = 1;
                    expanded[r + 2][c] = 1;
                } else if (grid[i].charAt(j) == '\\') {
                    expanded[r][c] = 1;
                    expanded[r + 1][c + 1] = 1;
                    expanded[r + 2][c + 2] = 1;
                }
            }
        }
        
        int regions = 0;
        // Count 0-islands
        for (int i = 0; i < n * 3; i++) {
            for (int j = 0; j < n * 3; j++) {
                if (expanded[i][j] == 0) {
                    dfs(expanded, i, j);
                    regions++;
                }
            }
        }
        return regions;
    }
    
    private static void dfs(int[][] expanded, int i, int j) {
        if (i < 0 || i >= expanded.length || j < 0 || j >= expanded[0].length || expanded[i][j] == 1) {
            return;
        }
        expanded[i][j] = 1; // Mark as visited
        dfs(expanded, i + 1, j);
        dfs(expanded, i - 1, j);
        dfs(expanded, i, j + 1);
        dfs(expanded, i, j - 1);
    }

    public static void main(String[] args) {
        String[] grid = {" /", "/ "};
        System.out.println("Regions cut by slashes: " + regionsBySlashes(grid)); // 2
    }
}