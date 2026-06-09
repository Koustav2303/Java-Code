/**
 * PROBLEM: Reverse a Doubly Linked List
 * * Given the head of a doubly linked list, reverse the node links in-place and return the new head reference.
 * * Strategy: Symmetric Temp Swapping Loop
 * Iterate through the doubly linked list, swapping the `next` and `prev` pointers for each individual node. 
 * Track the prior state context using a temporary reference variable to avoid losing the next node location 
 * before sliding the window forward.
 */
public class ReverseDoublyLinkedList {
    static class DLLNode {
        int val; DLLNode prev, next;
        DLLNode(int val) { this.val = val; }
    }

    public static DLLNode reverseDLL(DLLNode head) {
        DLLNode temp = null;
        DLLNode curr = head;

        while (curr != null) {
            // Swap bi-directional node references in-place safely
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;

            curr = curr.prev; // Advance backward using the updated pointer reference
        }

        // Adjust for final terminal state node boundaries to identify new head node reference
        return temp != null ? temp.prev : head;
    }

    public static void main(String[] args) {
        DLLNode n1 = new DLLNode(1); DLLNode n2 = new DLLNode(2);
        n1.next = n2; n2.prev = n1; // 1 <=> 2

        DLLNode res = reverseDLL(n1); // Outcome -> 2 <=> 1
        System.out.println("New reversed head node value reference: " + res.val); // 2
    }
}