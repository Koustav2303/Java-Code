/**
 * PROBLEM: Reverse Linked List II
 * * Given the head of a singly linked list and two integers left and right where left <= right, 
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 * * Strategy: Bounded Pointer Disconnection
 * Advance a tracking pointer `prev` to position `left - 1`. 
 * Use an internal sliding loop to reverse the nodes up to position `right` in-place. 
 * This lets you invert the target sub-segment without losing your connections to the rest of the list.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class ReverseLinkedListII {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // Step 1: Position prev right before the target reversal window
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode curr = prev.next;
        ListNode nextNode = curr.next;

        // Step 2: Reverse the nodes within the bounded segment window
        for (int i = 0; i < right - left; i++) {
            curr.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
            nextNode = curr.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2); head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4); head.next.next.next.next = new ListNode(5); // 1->2->3->4->5

        ListNode res = reverseBetween(head, 2, 4); // Reverse elements from indices 2 through 4 (2,3,4) -> 1->4->3->2->5
        System.out.print("Bounded Reverse Result: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}