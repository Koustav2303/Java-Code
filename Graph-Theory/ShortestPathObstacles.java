import java.util.LinkedList;
import java.util.Queue;

/**
 * PROBLEM: Shortest Path in a Grid with Obstacles Elimination
 * * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle).
 * You can move up, down, left, or right. You have at most k quotas to eliminate obstacles.
 * Return the minimum number of steps to walk from the upper left to the lower right.
 * * Approach:
 * BFS with State. A standard BFS keeps a boolean `visited` array. But here, returning to a cell 
 * with MORE obstacle elimination capacity than a previous visit is a valid path!
 * Thus, our `visited` array stores the integer "maximum remaining quotas k" we had when we reached that cell.
 */
public class ShortestPathObstacles {
    public static int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) return 0;
        
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = -1; // -1 means unvisited
            }
        }
        
        // Queue elements: {row, col, k_remaining, steps}
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, k, 0});
        visited[0][0] = k;
        
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], remK = curr[2], steps = curr[3];
            
            if (r == m - 1 && c == n - 1) return steps;
            
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextK = remK - grid[nr][nc]; // grid value is 1 (obstacle) or 0 (empty)
                    
                    // If we have enough quotas AND this path offers MORE quotas than our previous visit
                    if (nextK >= 0 && nextK > visited[nr][nc]) {
                        visited[nr][nc] = nextK;
                        queue.add(new int[]{nr, nc, nextK, steps + 1});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0},
            {1, 1, 0},
            {0, 0, 0},
            {0, 1, 1},
            {0, 0, 0}
        };
        System.out.println("Shortest path with 1 elimination: " + shortestPath(grid, 1)); // 6
    }
}