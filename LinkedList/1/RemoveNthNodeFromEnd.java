/**
 * PROBLEM: Remove Nth Node From End of List
 * * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * * Strategy: Fixed Gap Pointer Advancement
 * Maintain a fast and slow pointer separated by an exact gap of n nodes. 
 * Advance both at the same speed. When the fast pointer reaches the end, the slow pointer 
 * will sit right before the node that needs to be deleted.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class RemoveNthNodeFromEnd {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        // Advance fast pointer to establish the fixed window gap size
        for (int i = 0; i <= n; i++) {
            if (fast == null) return head; // Safety fallback
            fast = fast.next;
        }

        // Move both pointers at the exact same speed to maintain the gap boundary
        while (fast != null) {
            slow = slow.next;
            fast = fast.next; // Fixed: Changed from fast.next.next to fast.next
        }

        // Unlink the target node from the sequence chain
        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); 
        head.next = new ListNode(2); 
        head.next.next = new ListNode(3);
        
        ListNode res = removeNthFromEnd(head, 2); // Removes '2' from the end
        System.out.print("Remaining layout nodes: ");
        while (res != null) { 
            System.out.print(res.val + " "); 
            res = res.next; 
        } 
        System.out.println(); // Output should be: 1 3
    }
}