import java.util.HashSet;
import java.util.Set;

/**
 * PROBLEM: Remove Duplicates from Unsorted List
 * * Given the head of an unsorted linked list, remove all duplicate nodes such that each unique element 
 * appears exactly once, preserving original value alignment positions.
 * * Strategy: HashSet Look-Ahead Unlink
 * Track values seen during the traversal inside a HashSet. Use a look-ahead format checking `curr.next`. 
 * If `set.contains(curr.next.val)` matches true, unlink the node by adjusting the pointer to skip it: 
 * `curr.next = curr.next.next`.
 */
public class RemoveDuplicatesUnsortedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode removeDuplicates(ListNode head) {
        if (head == null) return null;

        Set<Integer> seen = new HashSet<>();
        ListNode curr = head;
        seen.add(head.val);

        while (curr != null && curr.next != null) {
            if (seen.contains(curr.next.val)) {
                curr.next = curr.next.next; // Sever link matching duplication constraints
            } else {
                seen.add(curr.next.val);
                curr = curr.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3); head.next = new ListNode(1); head.next.next = new ListNode(3);

        ListNode res = removeDuplicates(head); // Removes the duplicate 3 -> 3 1
        System.out.print("Deduplicated List: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}