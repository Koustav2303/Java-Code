/**
 * PROBLEM: Remove Duplicates from Sorted List II
 * * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving 
 * only distinct numbers from the original list. Return the linked list sorted as well.
 * * Strategy: Sibling Look-Ahead Loop
 * Use a dummy node pointing to the head. Maintain a pointer `prev`. For each node, check if its value 
 * matches the next node's value. If it does, run an inner loop to skip *all* nodes with that duplicate value.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class RemoveDuplicatesFromSortedListII {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            if (prev.next.val == prev.next.next.val) {
                int duplicateVal = prev.next.val;
                // Continuously skip all consecutive nodes sharing this duplicate value
                while (prev.next != null && prev.next.val == duplicateVal) {
                    prev.next = prev.next.next;
                }
            } else {
                prev = prev.next; // Move forward only if no duplicates were processed
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(1);
        head.next.next = new ListNode(2); head.next.next.next = new ListNode(3);

        ListNode res = deleteDuplicates(head);
        System.out.print("Distinct Layout nodes: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 2 3
        System.out.println();
    }
}