import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    public static int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        int rows = grid.length, cols = grid[0].length;

        // Count fresh oranges and add rotten ones to the queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) queue.add(new int[]{i, j});
                else if (grid[i][j] == 1) freshCount++;
            }
        }

        if (freshCount == 0) return 0;

        int minutes = 0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    // If we find a fresh orange, rot it
                    if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        freshCount--;
                        queue.add(new int[]{r, c});
                    }
                }
            }
            minutes++;
        }
        return freshCount == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println("Minutes to rot all oranges: " + orangesRotting(grid));
    }
}