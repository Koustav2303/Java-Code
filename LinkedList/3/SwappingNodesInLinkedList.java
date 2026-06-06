/**
 * PROBLEM: Swapping Nodes in a Linked List
 * * You are given the head of a linked list, and an integer k.
 * Return the head of the linked list after swapping the values of the kth node from the beginning 
 * and the kth node from the end (the list is 1-indexed).
 * * Strategy: Symmetric Window Pointer Convergence
 * Advance a `fast` pointer $k - 1$ steps forward to locate the K-th node from the beginning (`first`). 
 * Then, initialize a `second` pointer at the head. Advance both `fast` and `second` at the exact same speed 
 * until `fast` reaches the last node. `second` will be perfectly positioned at the K-th node from the end. 
 * Swap their values.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class SwappingNodesInLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode swapNodes(ListNode head, int k) {
        ListNode fast = head;
        ListNode first = head;
        ListNode second = head;

        // Position fast pointer at the K-th node from the start
        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }
        first = fast; // Pinpoint reference location

        // Move both pointers until fast hits the tail boundary node
        while (fast.next != null) {
            fast = fast.next;
            second = second.next;
        }

        // Swap node values in-place safely
        int temp = first.val;
        first.val = second.val;
        second.val = temp;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(3); head.next.next.next = new ListNode(4);

        ListNode res = swapNodes(head, 2); // Swaps 2nd from start (2) with 2nd from end (3) -> 1 3 2 4
        System.out.print("Symmetric swap outcome: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}