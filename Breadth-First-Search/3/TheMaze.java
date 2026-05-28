import java.util.*;

public class TheMaze {
    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        
        queue.add(start);
        visited[start[0]][start[1]] = true;
        
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == destination[0] && curr[1] == destination[1]) return true;
            
            for (int[] dir : dirs) {
                int r = curr[0], c = curr[1];
                // Keep rolling in the current direction until hitting a wall
                while (r + dir[0] >= 0 && r + dir[0] < m && c + dir[1] >= 0 && c + dir[1] < n && maze[r + dir[0]][c + dir[1]] == 0) {
                    r += dir[0];
                    c += dir[1];
                }
                
                if (!visited[r][c]) {
                    visited[r][c] = true;
                    queue.add(new int[]{r, c});
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = {
            {0,0,1,0,0},
            {0,0,0,0,0},
            {0,0,0,1,0},
            {1,1,0,1,1},
            {0,0,0,0,0}
        };
        System.out.println("Has path to dest? " + hasPath(maze, new int[]{0,4}, new int[]{4,4})); // true
    }
}