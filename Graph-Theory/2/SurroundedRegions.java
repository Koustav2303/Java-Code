import java.util.Arrays;

/**
 * PROBLEM: Surrounded Regions
 * * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally 
 * surrounded by 'X'. A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * * Critical Insight:
 * An 'O' can escape capture if and only if it touches the boundary of the board, or links to an 
 * 'O' that does. Thus, we start our search at the boundaries, mark all border-connected 'O's as 
 * un-capturable ('T'), then flip remaining 'O's to 'X' and revert 'T' back to 'O'.
 * * Complexity:
 * Time Complexity: O(M * N)
 * Space Complexity: O(M * N) for the recursive call stack.
 */
public class SurroundedRegions {
    public static void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        
        // Scan left and right borders
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][n - 1] == 'O') dfs(board, i, n - 1);
        }
        // Scan top and bottom borders
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[m - 1][j] == 'O') dfs(board, m - 1, j);
        }
        
        // Processes the final transformation states
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X'; // Captured
                else if (board[i][j] == 'T') board[i][j] = 'O'; // Reverted back to safe
            }
        }
    }
    
    private static void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'T'; // Temporary safe placeholder marker
        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }

    public static void main(String[] args) {
        char[][] board = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };
        solve(board);
        System.out.println("Board after processing surrounded regions:");
        for (char[] row : board) System.out.println(Arrays.toString(row));
    }
}