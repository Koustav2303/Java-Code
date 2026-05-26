public class NumberOfIslands {
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    sinkIsland(grid, i, j); // DFS to mark connected land as visited
                }
            }
        }
        return count;
    }

    private static void sinkIsland(char[][] grid, int i, int j) {
        // Boundary checks + stop if it's water
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        
        grid[i][j] = '0'; // Sink it
        
        // Check all 4 adjacent directions
        sinkIsland(grid, i + 1, j); // down
        sinkIsland(grid, i - 1, j); // up
        sinkIsland(grid, i, j + 1); // right
        sinkIsland(grid, i, j - 1); // left
    }

    public static void main(String[] args) {
        char[][] map = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("Total islands: " + numIslands(map));
    }
}