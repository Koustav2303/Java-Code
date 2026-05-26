import java.util.Arrays;

public class SurroundedRegions {
    public static void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int rows = board.length, cols = board[0].length;

        // 1. Check first and last column for safe 'O's
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') markSafe(board, i, 0);
            if (board[i][cols - 1] == 'O') markSafe(board, i, cols - 1);
        }

        // 2. Check first and last row for safe 'O's
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') markSafe(board, 0, j);
            if (board[rows - 1][j] == 'O') markSafe(board, rows - 1, j);
        }

        // 3. Flip all remaining 'O's to 'X' (captured), and 'T's back to 'O' (safe)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'T') board[i][j] = 'O';
            }
        }
    }

    private static void markSafe(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') return;
        
        board[i][j] = 'T'; // Mark as Temporarily Safe
        
        markSafe(board, i + 1, j);
        markSafe(board, i - 1, j);
        markSafe(board, i, j + 1);
        markSafe(board, i, j - 1);
    }

    public static void main(String[] args) {
        char[][] board = {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };
        solve(board);
        System.out.println("Board after capturing surrounded regions:");
        for (char[] row : board) System.out.println(Arrays.toString(row));
    }
}