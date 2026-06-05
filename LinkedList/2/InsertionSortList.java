/**
 * PROBLEM: Insertion Sort List
 * * Sort a linked list using insertion sort.
 * * Strategy: Dummy Chain Scan
 * Maintain a 'dummy' node acting as the head of a new sorted list. For each node in the original list, 
 * scan the sorted list from the beginning to find its correct insertion position, then splice it in.
 * * Complexity:
 * Time Complexity: $O(N^2)$
 * Space Complexity: O(1)
 */
public class InsertionSortList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next; // Cache remaining unsorted elements
            ListNode prev = dummy;

            // Find the position to insert the current node
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }

            // Splice current node into the sorted chain
            curr.next = prev.next;
            prev.next = curr;

            curr = nextTemp; // Move to next unsorted node
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4); head.next = new ListNode(2);
        head.next.next = new ListNode(1); head.next.next.next = new ListNode(3);

        ListNode sorted = insertionSortList(head);
        System.out.print("Insertion Sorted: ");
        while (sorted != null) { System.out.print(sorted.val + " "); sorted = sorted.next; } // 1 2 3 4
        System.out.println();
    }
}