/**
 * PROBLEM: Design Circular Deque
 * * Design a circular double-ended queue (Deque) with a fixed maximum size using a Doubly Linked List.
 * * Strategy: Guard Nodes & Capacity Counters
 * Maintain two dummy nodes (`head` and `tail`) linked to each other initially. 
 * Track the current size to enforce the maximum capacity constraints.
 */
public class DesignCircularDeque {
    static class Node {
        int val; Node prev, next;
        Node(int val) { this.val = val; }
    }

    private final int capacity;
    private int size;
    private final Node head;
    private final Node tail;

    public DesignCircularDeque(int k) {
        this.capacity = k;
        this.size = 0;
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public boolean insertFront(int value) {
        if (size == capacity) return false;
        Node newNode = new Node(value);
        newNode.next = head.next;
        newNode.prev = head;
        head.next.prev = newNode;
        head.next = newNode;
        size++;
        return true;
    }
    
    public boolean insertLast(int value) {
        if (size == capacity) return false;
        Node newNode = new Node(value);
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
        return true;
    }
    
    public boolean deleteFront() {
        if (size == 0) return false;
        head.next = head.next.next;
        head.next.prev = head;
        size--;
        return true;
    }
    
    public boolean deleteLast() {
        if (size == 0) return false;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        size--;
        return true;
    }
    
    public int getFront() { return size == 0 ? -1 : head.next.val; }
    public int getRear() { return size == 0 ? -1 : tail.prev.val; }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }

    public static void main(String[] args) {
        DesignCircularDeque deque = new DesignCircularDeque(3);
        System.out.print(deque.insertLast(1) + " ");  // true
        System.out.print(deque.insertLast(2) + " ");  // true
        System.out.print(deque.insertFront(3) + " "); // true (Layout: 3 <-> 1 <-> 2)
        System.out.println(deque.getRear());          // 2
    }
}