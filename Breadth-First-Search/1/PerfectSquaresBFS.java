import java.util.LinkedList;
import java.util.Queue;

public class PerfectSquaresBFS {
    public static int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        
        queue.add(n);
        visited[n] = true;
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                
                // Subtract all possible perfect squares
                for (int j = 1; j * j <= curr; j++) {
                    int next = curr - j * j;
                    if (next == 0) return steps;
                    
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int n = 12;
        System.out.println("Least perfect squares to sum to " + n + ": " + numSquares(n)); 
        // 3 (4 + 4 + 4)
    }
}