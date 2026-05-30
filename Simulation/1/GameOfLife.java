/**
 * PROBLEM: Game of Life
 * * The board is made up of an m x n grid of cells, where each cell has an initial state: 
 * live (1) or dead (0). Each cell interacts with its eight neighbors based on Conway's Rules.
 * You must solve it in-place (O(1) extra space).
 * * Rules:
 * 1. Live cell < 2 live neighbors -> Dies (Underpopulation)
 * 2. Live cell 2 or 3 live neighbors -> Lives
 * 3. Live cell > 3 live neighbors -> Dies (Overpopulation)
 * 4. Dead cell exactly 3 live neighbors -> Becomes Live (Reproduction)
 * * Approach:
 * To do this in-place without losing the original state for adjacent cells, 
 * use dummy values to represent transitions:
 * 2 = newly alive (was 0, now 1)
 * -1 = newly dead (was 1, now 0)
 */

import java.util.Arrays;

public class GameOfLife {
    public static void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        
        // Step 1: Simulate the transitions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = countLive(board, i, j, m, n);
                
                // Rule 1 or 3: Cell dies
                if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[i][j] = -1; // -1 means it was 1, but now is 0
                }
                // Rule 4: Cell becomes alive
                if (board[i][j] == 0 && liveNeighbors == 3) {
                    board[i][j] = 2; // 2 means it was 0, but now is 1
                }
            }
        }
        
        // Step 2: Finalize the state (convert dummy variables back to 0 and 1)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) board[i][j] = 0;
                if (board[i][j] == 2) board[i][j] = 1;
            }
        }
    }
    
    private static int countLive(int[][] board, int r, int c, int m, int n) {
        int count = 0;
        int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            // If the neighbor is 1, or it WAS 1 (currently -1), it counts as alive for this generation
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && Math.abs(board[nr][nc]) == 1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] board = {{0,1,0}, {0,0,1}, {1,1,1}, {0,0,0}};
        gameOfLife(board);
        System.out.println("Next Generation:");
        for (int[] row : board) System.out.println(Arrays.toString(row));
    }
}