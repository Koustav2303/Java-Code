import java.util.HashSet;
import java.util.Set;

/**
 * PROBLEM: Walking Robot Simulation
 * * A robot on an infinite XY-plane starts at (0, 0) and faces north. The robot receives an array of 
 * commands: -2 (turn left 90 deg), -1 (turn right 90 deg), or 1 <= k <= 9 (move forward k units).
 * Some of the grid squares are obstacles. If the robot hits an obstacle, it stops moving forward for 
 * that command but continues with the next command.
 * Return the maximum Euclidean distance squared that the robot ever gets from the origin.
 * * Approach:
 * Use a HashSet to store obstacle coordinates as strings "x,y" for O(1) lookups.
 * Simulate the robot moving step-by-step to catch collisions instantly.
 */
public class WalkingRobot {
    public static int robotSim(int[] commands, int[][] obstacles) {
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(obs[0] + "," + obs[1]);
        }
        
        // North, East, South, West
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dirIndex = 0; // Start facing North
        int x = 0, y = 0;
        int maxDistSquared = 0;
        
        for (int command : commands) {
            if (command == -1) { // Turn Right
                dirIndex = (dirIndex + 1) % 4;
            } else if (command == -2) { // Turn Left
                dirIndex = (dirIndex + 3) % 4;
            } else {
                // Move forward step-by-step
                for (int step = 0; step < command; step++) {
                    int nextX = x + dirs[dirIndex][0];
                    int nextY = y + dirs[dirIndex][1];
                    
                    // Stop completely for this command if obstacle hit
                    if (obstacleSet.contains(nextX + "," + nextY)) {
                        break;
                    }
                    
                    x = nextX;
                    y = nextY;
                    maxDistSquared = Math.max(maxDistSquared, x * x + y * y);
                }
            }
        }
        
        return maxDistSquared;
    }

    public static void main(String[] args) {
        int[] commands = {4, -1, 3};
        int[][] obstacles = {};
        System.out.println("Max distance squared: " + robotSim(commands, obstacles)); // 25
    }
}