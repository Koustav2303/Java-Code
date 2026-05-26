import java.util.Arrays;

public class GameOfLife {
    public static void gameOfLife(int[][] board) {
        int rows = board.length, cols = board[0].length;
        
        // 1. Evaluate board and encode future states
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = countLiveNeighbors(board, i, j);
                
                // Rule 1 or Rule 3 (Dies)
                if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[i][j] = 2; // 2 means: Live -> Dead
                }
                // Rule 4 (Reproduces)
                if (board[i][j] == 0 && liveNeighbors == 3) {
                    board[i][j] = 3; // 3 means: Dead -> Live
                }
            }
        }
        
        // 2. Decode the final board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 2) board[i][j] = 0;
                if (board[i][j] == 3) board[i][j] = 1;
            }
        }
    }

    private static int countLiveNeighbors(int[][] board, int r, int c) {
        int count = 0;
        int[][] directions = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
        
        for (int[] dir : directions) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];
            // Treat 1 (alive) and 2 (was alive) as currently alive neighbors
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length &&
                (board[newRow][newCol] == 1 || board[newRow][newCol] == 2)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] board = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
        };
        gameOfLife(board);
        System.out.println("Next Generation:");
        for (int[] row : board) System.out.println(Arrays.toString(row));
    }
}