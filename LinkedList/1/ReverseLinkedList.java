/**
 * PROBLEM: Reverse Linked List
 * * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * * Strategy: Preceding Reference Shift Loop
 * Maintain three pointers: `prev`, `curr`, and a temporary `nextTemp`. 
 * Iterate through the list, flipping each node's next pointer backwards to face `prev` 
 * before sliding the tracking window forward.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class ReverseLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next; // Cache remaining trail list
            curr.next = prev;              // Flip pointer reference backwards
            prev = curr;                   // Move prev forward
            curr = nextTemp;               // Move curr forward
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2); head.next.next = new ListNode(3);

        ListNode res = reverseList(head);
        System.out.print("Reversed output sequence: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 3 2 1
        System.out.println();
    }
}