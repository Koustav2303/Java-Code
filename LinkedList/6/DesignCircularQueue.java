/**
 * PROBLEM: Design Circular Queue
 * * Design an implementation of the Circular Queue data structure using a singly linked list. 
 * A circular queue is a linear data structure in which the operations are performed based on FIFO 
 * principle and the last position is connected back to the first position to make a circle.
 * * Strategy: Bounded Tail-to-Head Loop
 * Maintain a tracking `head` and `tail` pointer along with static `capacity` and `size` parameters. 
 * To optimize enqueue actions, connect the tail node's next pointer directly back to the head reference node.
 */
public class DesignCircularQueue {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    private ListNode head;
    private ListNode tail;
    private int size;
    private final int capacity;

    public DesignCircularQueue(int k) {
        this.capacity = k;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }
    
    public boolean enQueue(int value) {
        if (isFull()) return false;
        ListNode newNode = new ListNode(value);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head; // Close the circular ring link
        size++;
        return true;
    }
    
    public boolean deQueue() {
        if (isEmpty()) return false;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            tail.next = head; // Maintain the circular link configuration
        }
        size--;
        return true;
    }
    
    public int Front() { return isEmpty() ? -1 : head.val; }
    public int Rear() { return isEmpty() ? -1 : tail.val; }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }

    public static void main(String[] args) {
        DesignCircularQueue cq = new DesignCircularQueue(3);
        System.out.print(cq.enQueue(1) + " "); // true
        System.out.print(cq.enQueue(2) + " "); // true
        System.out.print(cq.enQueue(3) + " "); // true
        System.out.println(cq.Rear());         // 3
    }
}