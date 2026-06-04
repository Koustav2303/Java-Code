/**
 * PROBLEM: Word Search
 * * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally 
 * or vertically neighboring. The same letter cell may not be used more than once.
 * * Strategy: In-place Matrix Masking
 * Search for the starting char. When found, use DFS to explore neighbors. Temporary overwrite the 
 * cell with '#' to simulate a visited tracker without using extra memory space, then restore the character on return.
 */
public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, i, j, 0, word)) return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int r, int c, int index, String word) {
        if (index == word.length()) return true;
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        char originalChar = board[r][c];
        board[r][c] = '#'; // In-place path masking lock step

        boolean found = dfs(board, r + 1, c, index + 1, word) ||
                        dfs(board, r - 1, c, index + 1, word) ||
                        dfs(board, r, c + 1, index + 1, word) ||
                        dfs(board, r, c - 1, index + 1, word);

        board[r][c] = originalChar; // Release lock step back (Backtrack)
        return found;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        System.out.println("Word 'ABCCED' exists? " + exist(board, "ABCCED")); // true
    }
}