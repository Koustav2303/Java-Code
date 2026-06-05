/**
 * PROBLEM: Linked List Cycle II
 * * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * * Strategy: Floyd's Algorithm Mathematical Match
 * Use slow and fast pointers to detect a cycle. Once they meet, leave one pointer at the intersection point 
 * and reset the other to the head of the list. Move both pointers forward at the exact same speed (1 step). 
 * The node where they meet again is mathematically guaranteed to be the start of the cycle.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class LinkedListCycleII {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) return null;

        // Reset slow to head; keep fast at the meeting point
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next; // Move both at steady 1-step pace
        }
        return slow; // Cycle origin entry point
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode loopNode = new ListNode(2);
        head.next = loopNode; head.next.next = new ListNode(0); head.next.next.next = loopNode; // Loops back to 2

        ListNode cycleStart = detectCycle(head);
        System.out.println("Cycle entry node value: " + (cycleStart != null ? cycleStart.val : "No Cycle")); // 2
    }
}