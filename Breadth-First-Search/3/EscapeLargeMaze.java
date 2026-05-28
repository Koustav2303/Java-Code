import java.util.*;

public class EscapeLargeMaze {
    private static final int MAX_AREA = 20000;
    
    public static boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> blockSet = new HashSet<>();
        for (int[] b : blocked) blockSet.add(b[0] + "," + b[1]);
        
        return bfs(source, target, blockSet) && bfs(target, source, blockSet);
    }
    
    private static boolean bfs(int[] start, int[] end, Set<String> blocked) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(start);
        visited.add(start[0] + "," + start[1]);
        
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        
        while (!queue.isEmpty() && visited.size() <= MAX_AREA) {
            int[] curr = queue.poll();
            if (curr[0] == end[0] && curr[1] == end[1]) return true;
            
            for (int[] dir : dirs) {
                int r = curr[0] + dir[0], c = curr[1] + dir[1];
                String key = r + "," + c;
                
                if (r >= 0 && r < 1e6 && c >= 0 && c < 1e6 && !blocked.contains(key) && !visited.contains(key)) {
                    visited.add(key);
                    queue.add(new int[]{r, c});
                }
            }
        }
        return visited.size() > MAX_AREA; // If we expanded past the max possible blocked area, we are free
    }

    public static void main(String[] args) {
        int[][] blocked = {{0,1},{1,0}};
        int[] source = {0,0}, target = {0,2};
        System.out.println("Is escape possible? " + isEscapePossible(blocked, source, target)); // false
    }
}
