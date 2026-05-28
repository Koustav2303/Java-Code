import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBinaryMatrix {
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1}); // {row, col, path_length}
        grid[0][0] = 1; // Mark as visited by setting to 1

        int[][] dirs = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], dist = curr[2];

            if (r == n - 1 && c == n - 1) return dist; // Reached the end

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                    queue.add(new int[]{nr, nc, dist + 1});
                    grid[nr][nc] = 1; // Mark visited
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0},
            {1, 1, 0},
            {1, 1, 0}
        };
        System.out.println("Shortest path length: " + shortestPathBinaryMatrix(grid)); // 4
    }
}