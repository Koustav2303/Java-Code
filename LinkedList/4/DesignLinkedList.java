/**
 * PROBLEM: Design Linked List
 * * Implement a custom Singly Linked List class containing foundational low-level pointer methods: 
 * get(index), addAtHead(val), addAtTail(val), addAtIndex(index, val), and deleteAtIndex(index).
 * * Strategy: Sentinel Dummy Node Bounds
 * Maintain a sentinel dummy node as the structural head and trace a 'size' state parameter 
 * to easily catch out-of-bounds error lookups in constant time.
 */
public class DesignLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    private int size;
    private ListNode dummyHead;

    public DesignLinkedList() {
        size = 0;
        dummyHead = new ListNode(0);
    }
    
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        ListNode curr = dummyHead.next;
        while (index-- > 0) curr = curr.next;
        return curr.val;
    }
    
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;
        size++;
        ListNode prev = dummyHead;
        while (index-- > 0) prev = prev.next;
        
        ListNode newNode = new ListNode(val);
        newNode.next = prev.next;
        prev.next = newNode;
    }
    
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        size--;
        ListNode prev = dummyHead;
        while (index-- > 0) prev = prev.next;
        prev.next = prev.next.next;
    }

    public static void main(String[] args) {
        DesignLinkedList list = new DesignLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2); // Linked list becomes 1->2->3
        System.out.println("Node at index 1: " + list.get(1)); // 2
        list.deleteAtIndex(1);  // Linked list becomes 1->3
        System.out.println("Node at index 1 after deletion: " + list.get(1)); // 3
    }
}