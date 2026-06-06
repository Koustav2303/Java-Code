/**
 * PROBLEM: Swap Nodes in Pairs
 * * Given a linked list, swap every two adjacent nodes and return its head. 
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * * Strategy: 3-Way Pointer Shift Link
 * Use a dummy node pointing to the head. Maintain a `curr` tracking pointer. 
 * For each pair, dynamically adjust next links to alter order positions without losing the trailing rest list.
 */
public class SwapNodesInPairs {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null && curr.next.next != null) {
            ListNode first = curr.next;
            ListNode second = curr.next.next;

            // Execute the pairwise structural cross link rotation shift
            first.next = second.next;
            second.next = first;
            curr.next = second;

            curr = first; // Advance tracking pointer by two steps forward
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(3); head.next.next.next = new ListNode(4);

        ListNode res = swapPairs(head);
        System.out.print("Pairwise Swapped output: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 2 1 4 3
        System.out.println();
    }
}