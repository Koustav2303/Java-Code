/**
 * PROBLEM: Battleships in a Board
 * * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', 
 * return the number of the battleships on board.
 * Battleships can only be placed horizontally or vertically. There are no adjacent battleships.
 * * Constraint: Could you do it in one-pass, using only O(1) extra memory and without modifying the board?
 * * Approach:
 * Instead of a full DFS/BFS, we just simulate looking for the "head" of every battleship.
 * A cell is a valid head if it is 'X' AND the cell above it is not 'X' AND the cell to its left is not 'X'.
 */
public class Battleships {
    public static int countBattleships(char[][] board) {
        int count = 0;
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    // Check if it's a continuation of a ship from the top
                    if (i > 0 && board[i - 1][j] == 'X') continue;
                    // Check if it's a continuation of a ship from the left
                    if (j > 0 && board[i][j - 1] == 'X') continue;
                    
                    // If neither, it's the top-left start of a new ship
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'X', '.', '.', 'X'},
            {'.', '.', '.', 'X'},
            {'.', '.', '.', 'X'}
        };
        System.out.println("Total battleships: " + countBattleships(board)); // 2
    }
}