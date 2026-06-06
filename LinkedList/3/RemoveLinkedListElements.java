/**
 * PROBLEM: Remove Linked List Elements
 * * Given the head of a linked list and an integer val, remove all the nodes of the linked list 
 * that has Node.val == val, and return the new head.
 * * Strategy: Sentinel Look-Ahead Skip
 * Use a dummy node pointing to the head to gracefully handle deletions at the very front of the list. 
 * Traverse with a pointer `curr`, checking `curr.next`. If a target match is found, update `curr.next = curr.next.next`.
 */
public class RemoveLinkedListElements {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next; // Unlink target matching node instance
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(6); head.next.next.next = new ListNode(3);

        ListNode res = removeElements(head, 6);
        System.out.print("Filtered list output: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 1 2 3
        System.out.println();
    }
}