import java.util.*;

public class TheMazeII {
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        int[][] distance = new int[m][n];
        for (int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // {r, c, dist}
        pq.add(new int[]{start[0], start[1], 0});
        
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], dist = curr[2];
            
            if (r == destination[0] && c == destination[1]) return dist;
            
            for (int[] dir : dirs) {
                int nr = r, nc = c, steps = 0;
                while (nr + dir[0] >= 0 && nr + dir[0] < m && nc + dir[1] >= 0 && nc + dir[1] < n && maze[nr + dir[0]][nc + dir[1]] == 0) {
                    nr += dir[0];
                    nc += dir[1];
                    steps++;
                }
                
                if (distance[r][c] + steps < distance[nr][nc]) {
                    distance[nr][nc] = distance[r][c] + steps;
                    pq.add(new int[]{nr, nc, distance[nr][nc]});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] maze = {
            {0,0,1,0,0},
            {0,0,0,0,0},
            {0,0,0,1,0},
            {1,1,0,1,1},
            {0,0,0,0,0}
        };
        System.out.println("Shortest distance: " + shortestDistance(maze, new int[]{0,4}, new int[]{4,4})); // 12
    }
}