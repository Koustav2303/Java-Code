/**
 * PROBLEM: Reverse Nodes in k-Group
 * * Given the head of a linked list, reverse the nodes of the list k at a time and return its modified head.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * * Strategy: Target Count Validation Sieve
 * Count ahead to check if at least `k` nodes remain in the list. If not, preserve the remaining segment 
 * as-is and terminate. If yes, reverse those `k` nodes in-place, and recursively process the next group.
 */
public class ReverseNodesInKGroup {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode countCheck = head;
        int count = 0;
        
        // Step 1: Verify if k node items remain in the trailing queue list segment
        while (countCheck != null && count < k) {
            countCheck = countCheck.next;
            count++;
        }

        // If the remaining block is smaller than k, leave it un-reversed
        if (count < k) return head;

        // Step 2: Reverse the local k block segment window
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; i < k; i++) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // Step 3: Connect tail of the current reversed group to the head of the next processed group
        if (curr != null) {
            head.next = reverseKGroup(curr, k);
        }
        
        return prev; // New head of this reversed block segment
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(3); head.next.next.next = new ListNode(4);

        ListNode res = reverseKGroup(head, 2); // Reverse in blocks of 2 -> 2 1 4 3
        System.out.print("K-Group Reversed: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}