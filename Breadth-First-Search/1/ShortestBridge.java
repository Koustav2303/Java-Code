import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    public static int shortestBridge(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;
        
        // Step 1: Find the first island and mark it as 2 using DFS
        for (int i = 0; i < n && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, queue);
                    found = true;
                }
            }
        }
        
        // Step 2: Multi-source BFS from Island 1 to Island 2
        int steps = 0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    
                    if (r >= 0 && r < n && c >= 0 && c < n) {
                        if (grid[r][c] == 1) return steps; // Reached Island 2!
                        if (grid[r][c] == 0) {
                            grid[r][c] = 2; // Mark water as visited
                            queue.add(new int[]{r, c});
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    
    private static void dfs(int[][] grid, int r, int c, Queue<int[]> queue) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 1) return;
        grid[r][c] = 2; // Mark Island 1
        queue.add(new int[]{r, c});
        
        dfs(grid, r + 1, c, queue); dfs(grid, r - 1, c, queue);
        dfs(grid, r, c + 1, queue); dfs(grid, r, c - 1, queue);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 1, 0},
            {0, 0, 0},
            {0, 0, 1}
        };
        System.out.println("Shortest bridge length: " + shortestBridge(grid)); // 2
    }
}