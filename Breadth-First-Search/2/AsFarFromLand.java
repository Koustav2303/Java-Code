import java.util.LinkedList;
import java.util.Queue;

public class AsFarFromLand {
    public static int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        
        // Add all land cells to the queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        
        if (queue.isEmpty() || queue.size() == n * n) return -1; // No water or no land
        
        int distance = -1;
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                
                for (int[] dir : dirs) {
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    
                    if (r >= 0 && r < n && c >= 0 && c < n && grid[r][c] == 0) {
                        grid[r][c] = 1; // Mark as visited
                        queue.add(new int[]{r, c});
                    }
                }
            }
            distance++; // Increment distance per BFS level
        }
        
        return distance;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,0}, {0,0,0}, {0,0,0}};
        System.out.println("Max distance to nearest land: " + maxDistance(grid)); // 4
    }
}
