import java.util.*;

public class MinimumKnightMoves {
    public static int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.add(new int[]{0, 0});
        visited.add("0,0");
        
        int[][] dirs = {{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}};
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == x && curr[1] == y) return steps;
                
                for (int[] dir : dirs) {
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];
                    
                    // -2 buffer allows stepping slightly backward to optimize a forward path
                    if (nx >= -2 && ny >= -2 && visited.add(nx + "," + ny)) {
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Knight moves to (5, 5): " + minKnightMoves(5, 5)); // 4
    }
}