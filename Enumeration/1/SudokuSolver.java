/**
 * PROBLEM: Sudoku Solver
 * * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * A sudoku solution must satisfy all of the following rules:
 * 1. Each of the digits 1-9 must occur exactly once in each row.
 * 2. Each of the digits 1-9 must occur exactly once in each column.
 * 3. Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 * * Strategy: Backtracking Search Over a 2D Constraint Space
 * Scan cells sequentially. When an empty slot '.' is hit, enumerate characters '1' through '9'.
 * Test placing a character by running localized row, column, and sub-box safety checks. 
 * If valid, assign it and step forward. If the downstream path fails, clear the cell and backtrack.
 */
public class SudokuSolver {
    public static void solveSudoku(char[][] board) {
        solve(board);
    }

    private static boolean solve(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    // Enumerate all possible valid placement values
                    for (char val = '1'; val <= '9'; val++) {
                        if (isValidPlacement(board, r, c, val)) {
                            board[r][c] = val; // Speculative choice placement
                            
                            if (solve(board)) return true; // Found final solution matching layout
                            
                            board[r][c] = '.'; // Clear choice cell value (Backtrack)
                        }
                    }
                    return false; // Backtrack line completely blocked
                }
            }
        }
        return true; // Finished enumerating all empty slots successfully
    }

    private static boolean isValidPlacement(char[][] board, int row, int col, char val) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) return false; // Row clash check
            if (board[i][col] == val) return false; // Column clash check
            // Sub-box 3x3 evaluation algorithm logic index scaling
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == val) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku(board);
        System.out.println("Sudoku Solved! Top left row values: ");
        for (int j = 0; j < 9; j++) System.out.print(board[0][j] + " ");
        System.out.println();
    }
}