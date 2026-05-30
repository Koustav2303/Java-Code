import java.util.HashSet;

/**
 * PROBLEM: Valid Sudoku
 * * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated 
 * according to the following rules:
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * * Approach:
 * Use a HashSet to track seen strings. We can encode the state like "5 in row 0", 
 * "5 in col 0", and "5 in block 0-0". If `add()` returns false, it's a duplicate.
 */
public class ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char currentVal = board[i][j];
                
                if (currentVal != '.') {
                    // Encode the simulation rules into unique string identifiers
                    if (!seen.add(currentVal + " found in row " + i) ||
                        !seen.add(currentVal + " found in column " + j) ||
                        !seen.add(currentVal + " found in sub-box " + i / 3 + "-" + j / 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // A valid Sudoku board
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
        
        System.out.println("Is the Sudoku board valid? " + isValidSudoku(board)); // Output: true
        
        // Let's introduce a duplicate '5' in the top-left 3x3 sub-box to make it invalid
        board[0][1] = '5';
        System.out.println("Is the modified board valid? " + isValidSudoku(board)); // Output: false
    }
}