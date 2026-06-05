/**
 * PROBLEM: Rotate List
 * * Given the head of a linked list, rotate the list to the right by k places.
 * * Strategy: Circular Splice Break
 * Find the total length of the list and connect the tail node back to the head to form a circular ring. 
 * Calculate the effective rotation offset: `k = k % length`. Move `length - k` steps from the tail node 
 * to find the new split point, break the circle, and update the head reference.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class RotateList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Calculate length and locate tail node reference
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Connect tail back to head to form a circular loop
        tail.next = head;

        // Find the split point position index
        k = k % length;
        int stepsToNewTail = length - k;
        ListNode newTail = tail;
        
        while (stepsToNewTail-- > 0) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null; // Break the circular connection link
        
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2); head.next.next = new ListNode(3);

        ListNode res = rotateRight(head, 1); // Rotate right by 1 -> 3 1 2
        System.out.print("Rotated Ring outcome: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}