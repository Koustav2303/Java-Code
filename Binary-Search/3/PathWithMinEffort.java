import java.util.LinkedList;
import java.util.Queue;

public class PathWithMinEffort {
    public static int minimumEffortPath(int[][] heights) {
        int low = 0, high = 1000000;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (hasPathUnderEffort(heights, mid)) {
                high = mid; // Try a smaller effort limit
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    private static boolean hasPathUnderEffort(int[][] heights, int maxEffort) {
        int m = heights.length, n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            
            if (r == m - 1 && c == n - 1) return true; // Reached end
            
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int effort = Math.abs(heights[nr][nc] - heights[r][c]);
                    if (effort <= maxEffort) {
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] heights = {{1,2,2}, {3,8,2}, {5,3,5}};
        System.out.println("Minimum effort path: " + minimumEffortPath(heights)); // 2
    }
}