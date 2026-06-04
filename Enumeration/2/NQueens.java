import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROBLEM: N-Queens
 * * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * * Strategy: Row-by-Row Constraint Tracking
 * Place queens column by column for each row. Use a safety look-back utility method to check column, 
 * top-left diagonal, and bottom-left diagonal constraints before approving state execution.
 */
public class NQueens {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        backtrack(0, board, result);
        return result;
    }

    private static void backtrack(int row, char[][] board, List<List<String>> result) {
        if (row == board.length) {
            result.add(construct(board));
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (isValidPlacement(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(row + 1, board, result);
                board[row][col] = '.'; // Backtrack step
            }
        }
    }

    private static boolean isValidPlacement(char[][] board, int row, int col) {
        // Check column vertically upwards
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        // Check top-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        // Check top-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private static List<String> construct(char[][] board) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            path.add(new String(board[i]));
        }
        return path;
    }

    public static void main(String[] args) {
        System.out.println("N-Queens configuration count for 4x4: " + solveNQueens(4).size()); // 2 solutions
    }
}