/**
 * PROBLEM: Robot Return to Origin
 * * There is a robot starting at the position (0, 0), the origin, on a 2D plane. Given a sequence of its moves, 
 * judge if this robot ends up at (0, 0) after it completes its moves.
 * * Example:
 * Input: moves = "UD"
 * Output: true
 * * Approach:
 * Simulate the X and Y coordinate plane. U/D modifies Y, L/R modifies X.
 */
public class RobotReturnToOrigin {
    public static boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        
        for (char move : moves.toCharArray()) {
            if (move == 'U') y++;
            else if (move == 'D') y--;
            else if (move == 'R') x++;
            else if (move == 'L') x--;
        }
        
        return x == 0 && y == 0;
    }

    public static void main(String[] args) {
        System.out.println("Returns to origin? " + judgeCircle("UDLR")); // true
    }
}