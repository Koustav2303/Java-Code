/**
 * PROBLEM: Reorder List
 * * You are given the head of a singly linked-list. L0 -> L1 -> ... -> Ln-1 -> Ln
 * Reorder the list to be on the form: L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 * * Strategy: Mid-Reverse-Interleave Pipeline
 * 1. Find the middle of the list using slow/fast pointers.
 * 2. Split the list into two halves and reverse the second half.
 * 3. Interleave nodes from the two halves by alternating pointers.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class ReorderList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: Find midpoint
        ListNode slow = head; ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; fast = fast.next.next;
        }

        // Step 2: Reverse second half context
        ListNode prev = null;
        ListNode curr = slow.next;
        slow.next = null; // Split the list into two separate halves

        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // Step 3: Interleave the two halves
        ListNode first = head;
        ListNode second = prev; // Head of the reversed second half
        
        while (second != null) {
            ListNode t1 = first.next;
            ListNode t2 = second.next;

            first.next = second;
            second.next = t1;

            first = t1;
            second = t2;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(3); head.next.next.next = new ListNode(4);

        reorderList(head);
        System.out.print("Interleaved Reorder: ");
        while (head != null) { System.out.print(head.val + " "); head = head.next; } // 1 4 2 3
        System.out.println();
    }
}