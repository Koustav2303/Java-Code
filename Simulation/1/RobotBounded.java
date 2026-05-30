/**
 * PROBLEM: Robot Bounded In Circle
 * * On an infinite plane, a robot initially stands at (0, 0) and faces north.
 * The robot can receive one of three instructions:
 * "G": go straight 1 unit.
 * "L": turn 90 degrees to the left.
 * "R": turn 90 degrees to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 * * Approach:
 * After 1 complete cycle of instructions, the robot is BOUNDED if:
 * 1. It returns to the origin (0, 0) OR
 * 2. It is NOT facing North. (If it faces any other direction, executing the cycle 
 * 3 more times will mathematically bring it back to the origin).
 */
public class RobotBounded {
    public static boolean isRobotBounded(String instructions) {
        // Directions: North(0), East(1), South(2), West(3)
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0;
        int currentDir = 0; // Starts facing North
        
        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'G') {
                x += dirs[currentDir][0];
                y += dirs[currentDir][1];
            } else if (instruction == 'R') {
                currentDir = (currentDir + 1) % 4; // Turn right
            } else if (instruction == 'L') {
                currentDir = (currentDir + 3) % 4; // Turn left (equivalent to +3)
            }
        }
        
        // Bounded if it returned to origin OR changed direction
        return (x == 0 && y == 0) || currentDir != 0;
    }

    public static void main(String[] args) {
        String instructions = "GGLLGG";
        System.out.println("Is robot bounded? " + isRobotBounded(instructions)); // true
    }
}