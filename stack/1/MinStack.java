import java.util.Stack;

/**
 * PROBLEM: Min Stack
 * * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time O(1).
 * * Implement the MinStack class:
 * - MinStack() initializes the stack object.
 * - void push(int val) pushes the element val onto the stack.
 * - void pop() removes the element on the top of the stack.
 * - int top() gets the top element of the stack.
 * - int getMin() retrieves the minimum element in the stack.
 * * Strategy:
 * Use two separate stacks. The primary stack stores the actual values, and a secondary 
 * 'minStack' keeps track of the running minimum element at each step.
 * * Complexity:
 * Time Complexity: O(1) for all operations.
 * Space Complexity: O(N) to store elements.
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);
        // The current min is the lesser of the new value and the current top of the minStack
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        } else {
            minStack.push(minStack.peek());
        }
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Minimum element: " + minStack.getMin()); // -3
        minStack.pop();
        System.out.println("Top element after pop: " + minStack.top());    // 0
        System.out.println("Minimum element after pop: " + minStack.getMin()); // -2
    }
}