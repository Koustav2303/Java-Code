/**
 * PROBLEM: Minesweeper
 * * Let's play the minesweeper game!
 * You are given an m x n char matrix board representing the game board, and a click position.
 * - 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square.
 * - 'B' represents a revealed blank square, '1' to '8' represents how many mines are adjacent.
 * * Rules:
 * 1. If 'M' is clicked, change it to 'X' and game over.
 * 2. If 'E' is clicked and has adjacent mines, reveal the count.
 * 3. If 'E' is clicked and has NO adjacent mines, change to 'B' and recursively reveal all 8 neighbors.
 * * Approach:
 * Exact DFS simulation of the game's rules. Calculate adjacent mines first before deciding to branch.
 */
public class Minesweeper {
    public static char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0], c = click[1];
        
        if (board[r][c] == 'M') {
            board[r][c] = 'X'; // Rule 1: Boom!
            return board;
        }
        
        dfs(board, r, c);
        return board;
    }
    
    private static void dfs(char[][] board, int r, int c) {
        int m = board.length, n = board[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != 'E') return;
        
        int mines = getAdjacentMines(board, r, c, m, n);
        
        if (mines > 0) {
            board[r][c] = (char) ('0' + mines); // Rule 2
        } else {
            board[r][c] = 'B'; // Rule 3
            // Recursively reveal all 8 neighbors
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue;
                    dfs(board, r + i, c + j);
                }
            }
        }
    }
    
    private static int getAdjacentMines(char[][] board, int r, int c, int m, int n) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int nr = r + i, nc = c + j;
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'M', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'}
        };
        updateBoard(board, new int[]{3, 0});
        for (char[] row : board) System.out.println(row);
    }
}