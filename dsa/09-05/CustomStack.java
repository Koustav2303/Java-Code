public class CustomStack {
    private int[] stack = new int[5];
    private int top = -1;

    public void push(int val) {
        if (top == stack.length - 1) return;
        stack[++top] = val;
    }

    public int pop() {
        return (top == -1) ? -1 : stack[top--];
    }

    public static void main(String[] args) {
        CustomStack s = new CustomStack();
        s.push(10);
        s.push(20);
        System.out.println("Popped: " + s.pop());
    }
}