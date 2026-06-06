/**
 * PROBLEM: Remove Duplicates from Sorted List
 * * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. 
 * Return the linked list sorted as well.
 * * Strategy: Sibling Look-Ahead Skip
 * Traverse the list sequentially. If the current node shares the exact same value as its immediate 
 * next neighbor, adjust the pointer to bypass that neighbor: `curr.next = curr.next.next`.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class DeleteDuplicatesSorted {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next; // Bypass adjacent duplicate reference
            } else {
                curr = curr.next; // Advance only when uniqueness is validated
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(1); head.next.next = new ListNode(2);

        ListNode res = deleteDuplicates(head);
        System.out.print("Deduplicated List: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 1 2
        System.out.println();
    }
}