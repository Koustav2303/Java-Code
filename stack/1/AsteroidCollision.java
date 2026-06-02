import java.util.Stack;
import java.util.Arrays;

/**
 * PROBLEM: Asteroid Collision
 * * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction 
 * (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. 
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * * Strategy: Simulation via Stack
 * Asteroids collide ONLY when the stack top is moving right (+) and the current asteroid is moving left (-).
 * Use a loop to resolve collisions down the stack.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class AsteroidCollision {
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int ast : asteroids) {
            boolean alive = true;
            
            // Collision condition loop
            while (alive && ast < 0 && !stack.isEmpty() && stack.peek() > 0) {
                if (stack.peek() < Math.abs(ast)) {
                    stack.pop(); // The right-moving asteroid is smaller and explodes. Keep checking down stack.
                    continue;
                } else if (stack.peek() == Math.abs(ast)) {
                    stack.pop(); // Both are identical size, both explode.
                }
                alive = false; // The current left-moving asteroid explodes or was neutralized.
            }
            
            if (alive) {
                stack.push(ast);
            }
        }
        
        // Map the remaining stack values back into a primitive array layout
        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] asteroids = {5, 10, -5};
        int[] asteroids2 = {10, 2, -5};
        System.out.println("Final state: " + Arrays.toString(asteroidCollision(asteroids)));  // [5, 10]
        System.out.println("Final state: " + Arrays.toString(asteroidCollision(asteroids2))); // [10]
    }
}