/**
 * PROBLEM: Move Zeroes to Front of Linked List
 * * Given a singly linked list, move all nodes containing value 0 to the front of the list, 
 * preserving the relative alignment configuration of all remaining elements.
 * * Strategy: Detach and Re-Link Head Insertion
 * Traverse the list using a single pointer look-ahead format checking `curr.next`. If `curr.next.val == 0`, 
 * decouple that zero node from its current sequence position and link it directly to the front 
 * of the tracking head anchor node in constant time.
 */
public class MoveZeroesToFront {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode moveZeroes(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.val == 0) {
                ListNode zeroNode = curr.next;
                curr.next = zeroNode.next; // Bypass unlinked node element position
                
                zeroNode.next = head;      // Splice zeroNode directly to the front
                head = zeroNode;           // Update head anchor tracking point
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(0); head.next.next = new ListNode(2);

        ListNode res = moveZeroes(head); // Outcome -> 0 1 2
        System.out.print("Zero Shift Output: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}