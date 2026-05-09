import java.util.Stack;

public class QueueEngine {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void enqueue(int x) { s1.push(x); }

    public int dequeue() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) s2.push(s1.pop());
        }
        return s2.isEmpty() ? -1 : s2.pop();
    }

    public static void main(String[] args) {
        QueueEngine q = new QueueEngine();
        q.enqueue(1); q.enqueue(2);
        System.out.println("Dequeued: " + q.dequeue());
    }
}