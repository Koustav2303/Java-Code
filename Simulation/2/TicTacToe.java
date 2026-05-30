/**
 * PROBLEM: Design Tic-Tac-Toe
 * * Assume the following rules are for a tic-tac-toe game on an n x n board:
 * 1. A move is guaranteed to be valid and is placed on an empty block.
 * 2. Once a winning condition is reached, no more moves are allowed.
 * 3. A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * * Implement the TicTacToe class:
 * - TicTacToe(int n) Initializes the object the size of the board n.
 * - int move(int row, int col, int player) Indicates that the player with id player (1 or 2) 
 * makes a move at the cell (row, col). Returns the winning player (1 or 2), or 0 if no one wins.
 * * Approach:
 * Instead of traversing the board to check for a win every time, we simulate the score.
 * Player 1 adds 1, Player 2 adds -1 to the respective row, col, and diagonals.
 * If any row, col, or diagonal reaches exactly 'n' or '-n', that player wins in O(1) time.
 */
public class TicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;
    private int n;

    public TicTacToe(int n) {
        this.n = n;
        rows = new int[n];
        cols = new int[n];
    }
    
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;
        
        rows[row] += toAdd;
        cols[col] += toAdd;
        
        // Main diagonal (top-left to bottom-right)
        if (row == col) {
            diagonal += toAdd;
        }
        
        // Anti-diagonal (top-right to bottom-left)
        if (col == (n - row - 1)) {
            antiDiagonal += toAdd;
        }
        
        // Check if this move won the game
        if (Math.abs(rows[row]) == n || 
            Math.abs(cols[col]) == n || 
            Math.abs(diagonal) == n || 
            Math.abs(antiDiagonal) == n) {
            return player;
        }
        
        return 0; // No winner yet
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3);
        System.out.println("Winner: " + game.move(0, 0, 1)); // 0
        System.out.println("Winner: " + game.move(0, 2, 2)); // 0
        System.out.println("Winner: " + game.move(1, 1, 1)); // 0
        System.out.println("Winner: " + game.move(2, 2, 2)); // 0
        System.out.println("Winner: " + game.move(2, 2, 1)); // Invalid conceptually, but simulating the rule.
        System.out.println("Winner: " + game.move(2, 0, 1)); // 0
        System.out.println("Winner: " + game.move(1, 0, 1)); // 1 (Player 1 wins on left column)
    }
}