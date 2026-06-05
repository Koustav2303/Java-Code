/**
 * PROBLEM: Sort List
 * * Given the head of a linked list, return the list after sorting it in ascending order.
 * * Strategy: Top-Down Merge Sort
 * Divide the list into two halves using slow/fast pointers. Disconnect the two halves, 
 * recursively sort both, and merge them back together using a standard sorted merge subroutine.
 * * Complexity:
 * Time Complexity: $O(N \log N)$
 * Space Complexity: $O(\log N)$ recursive call stack depth footprint.
 */
public class SortList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Step 1: Split the list into two halves using slow/fast pointers
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // Disconnect the first half from the second half

        // Step 2: Recursively sort each half independently
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // Step 3: Merge the sorted halves back together
        return merge(l1, l2);
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) { curr.next = l1; l1 = l1.next; }
            else { curr.next = l2; l2 = l2.next; }
            curr = curr.next;
        }
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4); head.next = new ListNode(2);
        head.next.next = new ListNode(1); head.next.next.next = new ListNode(3);

        ListNode sorted = sortList(head);
        System.out.print("Merge Sorted: ");
        while (sorted != null) { System.out.print(sorted.val + " "); sorted = sorted.next; } // 1 2 3 4
        System.out.println();
    }
}