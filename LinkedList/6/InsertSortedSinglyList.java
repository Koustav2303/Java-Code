/**
 * PROBLEM: Insert into a Sorted Singly Linked List
 * * Given the head of a sorted singly linked list and an integer value insertVal, 
 * insert a new node with value insertVal into the list such that the list remains sorted.
 * * Strategy: Sentinel Traversal Link
 * Utilize a sentinel dummy node tracking pointer initialization step to handle insertions directly before 
 * the original list head gracefully. Scan forward until finding a node whose next neighbor has a value 
 * greater than or equal to `insertVal`, then splice the element in-place.
 */
public class InsertSortedSinglyList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode insertSorted(ListNode head, int insertVal) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        // Move forward while the next element value is smaller than target input data
        while (curr.next != null && curr.next.val < insertVal) {
            curr = curr.next;
        }

        ListNode newNode = new ListNode(insertVal);
        newNode.next = curr.next;
        curr.next = newNode;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(3); head.next.next = new ListNode(4);

        ListNode res = insertSorted(head, 2); // Insert 2 -> 1 2 3 4
        System.out.print("Sorted Insertion Outcome: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}