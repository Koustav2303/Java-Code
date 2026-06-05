/**
 * PROBLEM: Linked List Cycle
 * * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * * Strategy: Pointer Speed Differential Tracking
 * Initialize a slow pointer (moving 1 step) and a fast pointer (moving 2 steps). 
 * If the list contains a cycle, the fast pointer will eventually loop around and catch up to 
 * the slow pointer from behind. If it hits null, there is no cycle.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class LinkedListCycle {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true; // Cycle detected
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode loopNode = new ListNode(2);
        head.next = loopNode; loopNode.next = new ListNode(0); loopNode.next.next = loopNode; // Cycle loops to 2

        System.out.println("Contains cycle loop? " + hasCycle(head)); // true
    }
}