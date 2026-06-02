import java.util.Stack;

/**
 * PROBLEM: Validate Stack Sequences
 * * Given two integer arrays pushed and popped each with distinct values, return true if this 
 * could have been the result of a sequence of push and pop operations on an initially empty stack, 
 * or false otherwise.
 * * Example:
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following operations:
 * push(1), push(2), push(3), push(4), pop() -> 4, push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * * Strategy: Simulation
 * Iterate through the `pushed` array and add elements to a stack. After each push, check if the top 
 * of the stack matches the current index element of `popped`. If it does, continuously pop until it mismatch.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class ValidateStackSequences {
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int popIdx = 0;
        
        for (int val : pushed) {
            stack.push(val);
            // Check if the pushed element satisfies an immediate pop condition
            while (!stack.isEmpty() && stack.peek() == popped[popIdx]) {
                stack.pop();
                popIdx++;
            }
        }
        
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 5, 3, 2, 1};
        System.out.println("Is valid stack sequence? " + validateStackSequences(pushed, popped)); // true
    }
}