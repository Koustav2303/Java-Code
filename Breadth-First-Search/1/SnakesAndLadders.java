import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    public static int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(1);
        visited[1] = true;
        int rolls = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n * n) return rolls;
                
                for (int dice = 1; dice <= 6 && curr + dice <= n * n; dice++) {
                    int next = curr + dice;
                    int[] pos = getCoordinates(next, n);
                    int r = pos[0], c = pos[1];
                    
                    // If there's a snake or ladder, take it
                    int destination = board[r][c] != -1 ? board[r][c] : next;
                    
                    if (!visited[destination]) {
                        visited[destination] = true;
                        queue.add(destination);
                    }
                }
            }
            rolls++;
        }
        return -1;
    }
    
    private static int[] getCoordinates(int square, int n) {
        int r = n - 1 - (square - 1) / n;
        int c = (square - 1) % n;
        // If the row from the bottom is odd, the columns go right-to-left
        if ((n - 1 - r) % 2 != 0) {
            c = n - 1 - c;
        }
        return new int[]{r, c};
    }

    public static void main(String[] args) {
        int[][] board = {
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1}
        };
        System.out.println("Minimum rolls: " + snakesAndLadders(board)); // 4
    }
}