import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlantic {
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        Queue<int[]> pacificQ = new LinkedList<>();
        Queue<int[]> atlanticQ = new LinkedList<>();
        
        // Add borders to respective queues
        for (int i = 0; i < m; i++) {
            pacificQ.add(new int[]{i, 0});
            atlanticQ.add(new int[]{i, n - 1});
        }
        for (int j = 0; j < n; j++) {
            pacificQ.add(new int[]{0, j});
            atlanticQ.add(new int[]{m - 1, j});
        }
        
        boolean[][] pacificReachable = bfs(heights, pacificQ);
        boolean[][] atlanticReachable = bfs(heights, atlanticQ);
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }
    
    private static boolean[][] bfs(int[][] heights, Queue<int[]> queue) {
        int m = heights.length, n = heights[0].length;
        boolean[][] reachable = new boolean[m][n];
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        for (int[] cell : queue) reachable[cell[0]][cell[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Flow UPHILL: The neighbor must be >= the current cell to flow into it
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !reachable[nr][nc] && heights[nr][nc] >= heights[r][c]) {
                    reachable[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        return reachable;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
        };
        System.out.println("Cells that flow to both oceans: " + pacificAtlantic(matrix));
    }
}