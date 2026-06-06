import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Remove Zero Sum Consecutive Nodes from Linked List
 * * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 
 * until no such sequences remain. After doing so, return the head of the final linked list.
 * * Strategy: Prefix-Sum Overwrite Map
 * Maintain a map tracking running prefix sums to their corresponding nodes. 
 * Pass 1 populates the map. If a prefix sum repeats, the later node overwrites the earlier entry. 
 * Pass 2 updates the `next` pointers: setting `curr.next = map.get(prefixSum).next` automatically 
 * cuts out any intermediate sub-segments that sum to zero.
 */
public class RemoveZeroSumConsecutiveNodes {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        Map<Integer, ListNode> map = new HashMap<>();
        int prefixSum = 0;
        
        // Pass 1: Record the last seen node for each prefix sum value
        for (ListNode curr = dummy; curr != null; curr = curr.next) {
            prefixSum += curr.val;
            map.put(prefixSum, curr);
        }

        prefixSum = 0;
        // Pass 2: Connect each node to the last node with the same prefix sum to clear zero-sum loops
        for (ListNode curr = dummy; curr != null; curr = curr.next) {
            prefixSum += curr.val;
            curr.next = map.get(prefixSum).next;
        }
        
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(-3); head.next.next.next = new ListNode(4); // 1->2->-3->4 -> 1+2-3 = 0, leaving 4

        ListNode res = removeZeroSumSublists(head);
        System.out.print("Remaining structural nodes: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 4
        System.out.println();
    }
}