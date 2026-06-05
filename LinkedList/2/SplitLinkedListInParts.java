import java.util.Arrays;

/**
 * PROBLEM: Split Linked List in Parts
 * * Given the head of a singly linked list and an integer k, split the linked list into k consecutive parts.
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than one.
 * * Strategy: Width & Remainder Allocation
 * Find the total length of the list. Calculate the baseline width for each part (`width = length / k`) 
 * and the number of remainder nodes (`rem = length % k`). Distribute one remainder node to each of 
 * the first `rem` parts to balance the sizes evenly.
 * * Complexity:
 * Time Complexity: O(N + k)
 * Space Complexity: O(k) for the result array structure.
 */
public class SplitLinkedListInParts {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] parts = new ListNode[k];
        int length = 0;
        ListNode curr = head;
        while (curr != null) { length++; curr = curr.next; }

        int baseWidth = length / k;
        int rem = length % k;

        curr = head;
        for (int i = 0; i < k; i++) {
            if (curr == null) break;
            parts[i] = curr;
            
            // Calculate the size of the current part
            int currentPartSize = baseWidth + (i < rem ? 1 : 0);
            for (int j = 1; j < currentPartSize; j++) {
                curr = curr.next;
            }

            ListNode nextPartHead = curr.next;
            curr.next = null; // Sever the link to isolate the current part
            curr = nextPartHead;
        }
        return parts;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2); head.next.next = new ListNode(3);

        ListNode[] res = splitListToParts(head, 5); // Split 3 elements into 5 parts -> [[1], [2], [3], null, null]
        System.out.println("Total parts allocated array size: " + res.length);
        System.out.println("First part root value: " + (res[0] != null ? res[0].val : "null")); // 1
    }
}