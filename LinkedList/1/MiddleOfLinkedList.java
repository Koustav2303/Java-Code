/**
 * PROBLEM: Middle of the Linked List
 * * Given the head of a singly linked list, return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 * * Strategy: Half-Speed Step Delta
 * Advance a fast pointer at double-speed (2 nodes) and a slow pointer at single-speed (1 node). 
 * When the fast pointer reaches the end of the list, the slow pointer will be perfectly positioned 
 * at the midpoint.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class MiddleOfLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(3); head.next.next.next = new ListNode(4);

        System.out.println("Middle node position value: " + middleNode(head).val); // 3
    }
}