import java.util.Stack;

/**
 * PROBLEM: Implement Queue using Stacks
 * * Implement a first in first out (FIFO) queue using only two stacks. 
 * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 * * Strategy: Dual Stacks (Input and Output Stacks)
 * Elements are pushed directly to the 'input' stack.
 * When a pop or peek action is triggered, if the 'output' stack is empty, we transfer all elements 
 * from 'input' to 'output' via pop-pushes. This completely reverses the LIFO order back into a FIFO layout!
 * * Complexity:
 * Time Complexity: Amortized O(1) per operation.
 * Space Complexity: O(N)
 */
public class QueueUsingStacks {
    private Stack<Integer> input;
    private Stack<Integer> output;

    public QueueUsingStacks() {
        input = new Stack<>();
        output = new Stack<>();
    }
    
    public void push(int x) {
        input.push(x);
    }
    
    public int pop() {
        peek(); // Guarantees elements are shifted into the output stack
        return output.pop();
    }
    
    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.peek();
    }
    
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    public static void main(String[] args) {
        QueueUsingStacks myQueue = new QueueUsingStacks();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println("Front item peek: " + myQueue.peek()); // 1
        System.out.println("Popped front item: " + myQueue.pop());  // 1
        System.out.println("Is queue empty? " + myQueue.empty()); // false
    }
}