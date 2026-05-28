import java.util.*;

public class FindSafestPathGrid {
    public static int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] safeness = new int[n][n];
        for (int[] row : safeness) Arrays.fill(row, -1);
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    queue.add(new int[]{i, j});
                    safeness[i][j] = 0;
                }
            }
        }
        
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        // BFS to calculate safeness factor of each cell
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : dirs) {
                int nr = curr[0] + dir[0], nc = curr[1] + dir[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && safeness[nr][nc] == -1) {
                    safeness[nr][nc] = safeness[curr[0]][curr[1]] + 1;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        
        // Max-Heap to find path maximizing the minimum safeness
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        pq.add(new int[]{0, 0, safeness[0][0]});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], safe = curr[2];
            
            if (r == n - 1 && c == n - 1) return safe;
            
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    pq.add(new int[]{nr, nc, Math.min(safe, safeness[nr][nc])});
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        List<List<Integer>> grid = Arrays.asList(
            Arrays.asList(1,0,0),
            Arrays.asList(0,0,0),
            Arrays.asList(0,0,1)
        );
        System.out.println("Maximum safeness factor: " + maximumSafenessFactor(grid)); // 0
    }
}