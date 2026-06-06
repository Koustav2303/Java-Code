/**
 * PROBLEM: Merge Nodes in Between Zeros
 * * You are given the head of a linked list, which contains a series of integers separated by 0s. 
 * The beginning and end of the linked list will have Node.val == 0.
 * Merge all the nodes lying between more consecutive 0s into a single node whose value is the sum 
 * of all the merged nodes. The modified list should not contain any 0s.
 * * Strategy: Two-Pointer Modification Sieve
 * Use a write pointer (`modify`) and a read pointer (`curr`). Accumulate values into a running sum 
 * until `curr` hits a zero node. When it does, write the accumulated sum to `modify.val`, 
 * link `modify` to the next segment head, and reset the running sum.
 */
public class MergeNodesInBetweenZeros {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeNodes(ListNode head) {
        ListNode modify = head.next; // First node containing a valid sum segment item
        ListNode curr = modify;
        
        int runningSum = 0;
        while (curr != null) {
            if (curr.val == 0) {
                modify.val = runningSum; // Write the segment total back to the write pointer
                runningSum = 0;          // Reset tracking counter
                modify.next = curr.next; // Link to next segment head, bypassing the current zero node
                modify = modify.next;    // Advance write pointer
            } else {
                runningSum += curr.val;
            }
            curr = curr.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0); head.next = new ListNode(3);
        head.next.next = new ListNode(1); head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(4); head.next.next.next.next.next = new ListNode(0); // 0->3->1->0->4->0

        ListNode res = mergeNodes(head);
        System.out.print("Merged Block Totals: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 4 4
        System.out.println();
    }
}