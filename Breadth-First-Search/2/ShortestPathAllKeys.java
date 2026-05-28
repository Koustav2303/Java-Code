import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathAllKeys {
    public static int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int totalKeys = 0;
        int startR = -1, startC = -1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') { startR = i; startC = j; }
                if (c >= 'a' && c <= 'f') totalKeys++;
            }
        }
        
        int targetBitmask = (1 << totalKeys) - 1;
        Queue<int[]> queue = new LinkedList<>(); // {row, col, keys_bitmask, steps}
        boolean[][][] visited = new boolean[m][n][1 << totalKeys];
        
        queue.add(new int[]{startR, startC, 0, 0});
        visited[startR][startC][0] = true;
        
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], keys = curr[2], steps = curr[3];
            
            if (keys == targetBitmask) return steps;
            
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    char cell = grid[nr].charAt(nc);
                    if (cell == '#') continue; // Wall
                    
                    if (cell >= 'A' && cell <= 'F' && (keys & (1 << (cell - 'A'))) == 0) {
                        continue; // Lock without key
                    }
                    
                    int newKeys = keys;
                    if (cell >= 'a' && cell <= 'f') {
                        newKeys |= (1 << (cell - 'a')); // Pick up key
                    }
                    
                    if (!visited[nr][nc][newKeys]) {
                        visited[nr][nc][newKeys] = true;
                        queue.add(new int[]{nr, nc, newKeys, steps + 1});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] grid = {"@.a..", "###.#", "b.A.B"};
        System.out.println("Shortest path to all keys: " + shortestPathAllKeys(grid)); // 8
    }
}