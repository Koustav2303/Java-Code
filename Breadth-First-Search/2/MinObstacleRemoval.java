import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinObstacleRemoval {
    public static int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] minCost = new int[m][n];
        for (int[] row : minCost) Arrays.fill(row, Integer.MAX_VALUE);
        
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0, 0});
        minCost[0][0] = 0;
        
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0], c = curr[1];
            
            if (r == m - 1 && c == n - 1) return minCost[r][c];
            
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newCost = minCost[r][c] + grid[nr][nc];
                    
                    if (newCost < minCost[nr][nc]) {
                        minCost[nr][nc] = newCost;
                        // 0-1 BFS Logic
                        if (grid[nr][nc] == 0) {
                            deque.addFirst(new int[]{nr, nc}); // Process 0 cost immediately
                        } else {
                            deque.addLast(new int[]{nr, nc});  // Delay 1 cost 
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 1, 1},
            {1, 1, 0},
            {1, 1, 0}
        };
        System.out.println("Minimum obstacles to remove: " + minimumObstacles(grid)); // 2
    }
}