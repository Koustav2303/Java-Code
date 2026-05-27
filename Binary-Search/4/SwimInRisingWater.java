import java.util.LinkedList;
import java.util.Queue;

public class SwimInRisingWater {
    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        int low = grid[0][0], high = n * n - 1;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canReachEnd(grid, mid)) {
                high = mid; // Try to do it faster (lower water level)
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    private static boolean canReachEnd(int[][] grid, int time) {
        int n = grid.length;
        if (grid[0][0] > time) return false;
        
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0] == n - 1 && curr[1] == n - 1) return true;
            
            for (int[] d : dirs) {
                int r = curr[0] + d[0];
                int c = curr[1] + d[1];
                if (r >= 0 && r < n && c >= 0 && c < n && !visited[r][c] && grid[r][c] <= time) {
                    visited[r][c] = true;
                    q.add(new int[]{r, c});
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 2}, {1, 3}};
        System.out.println("Minimum time to swim across: " + swimInWater(grid)); // 3
    }
}