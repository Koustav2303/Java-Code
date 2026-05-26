import java.util.Arrays;
import java.util.Stack;

public class AsteroidCollision {
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int ast : asteroids) {
            boolean exploded = false;
            
            // Collision only happens if stack top is moving RIGHT (+) and current is moving LEFT (-)
            while (!stack.isEmpty() && stack.peek() > 0 && ast < 0) {
                if (Math.abs(stack.peek()) < Math.abs(ast)) {
                    stack.pop(); // Stack asteroid explodes, current continues to check
                    continue;
                } else if (Math.abs(stack.peek()) == Math.abs(ast)) {
                    stack.pop(); // Both explode
                }
                exploded = true; // Current asteroid exploded
                break;
            }
            
            if (!exploded) {
                stack.push(ast);
            }
        }
        
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] asteroids = {5, 10, -5};
        System.out.println("Remaining asteroids: " + Arrays.toString(asteroidCollision(asteroids)));
        // Output: [5, 10]
    }
}