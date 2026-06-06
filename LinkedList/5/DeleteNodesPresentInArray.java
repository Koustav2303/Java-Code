import java.util.HashSet;
import java.util.Set;

/**
 * PROBLEM: Delete Nodes From Linked List Present in Array
 * * Given an array of integers nums and the head of a linked list, remove all nodes from the linked list 
 * whose values appear in nums, and return the new head.
 * * Strategy: HashSet Look-Ahead Skip
 * Load the array values into a HashSet to allow O(1) checks. Use a dummy node pointing to the head 
 * to easily handle deletion at the front of the list, then traverse and skip matching nodes.
 * * Complexity:
 * Time Complexity: O(N + M) where N is list length and M is array size.
 * Space Complexity: O(M) for the hash set.
 */
public class DeleteNodesPresentInArray {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode removeNodes(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null) {
            if (set.contains(curr.next.val)) {
                curr.next = curr.next.next; // Bypass matching node
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(3); head.next.next.next = new ListNode(4);
        int[] nums = {1, 3};

        ListNode res = removeNodes(nums, head);
        System.out.print("Filtered list output: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 2 4
        System.out.println();
    }
}